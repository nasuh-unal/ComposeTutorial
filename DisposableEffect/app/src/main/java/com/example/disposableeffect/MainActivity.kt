package com.example.disposableeffect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disposableeffect.ui.theme.DisposableEffectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisposableEffectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RunTimerScreen()
                }
            }
        }
    }
}

@Composable
fun TimerScreen() {
    println("1")
    val elapsedTime = remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        println("2")
        val scope = CoroutineScope(Dispatchers.Default)
        val job = scope.launch {
            while (true) {
                delay(1000)
                elapsedTime.value += 1
                println("Timer is still working ${elapsedTime.value}")
            }
        }

        onDispose {
            println("3")
            job.cancel()
            println("Timer is cancel ${elapsedTime.value}")
        }
    }

    Text(
        text = "Elapsed Time: ${elapsedTime.value}",
        modifier = Modifier.padding(16.dp),
        fontSize = 24.sp
    )
}

@Composable
fun RunTimerScreen() {
    val isVisible = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        if (isVisible.value)
            TimerScreen()

        Button(onClick = { isVisible.value = false }) {
            Text("Hide the timer")
        }
    }
}

/*
    - Disposable effect ilk çalıştırıldığında LaunchEffect gibi çalışır fakat composable
    uı hiyerarşisinden kaldırıldığında onDispose tetiklenerek işlemlerimizi durdurabilirz

    - onDsipose composable uı hiyerarşisinden kaldırıldığıında tetiklenir bu durum
    kaynakları temizlememizi sağlar.

    - Sayfa değişikliğinde onDispose tetiklenir.

    - Uygulama ilk ayağa kalktığında DisposableEffect tetiklenir fakat onDispose çalışmaz

    - DisposableEffect parametresi değiştiğinde ilk olarak onDispose çalışır ardından
    DisposableEffect çalışır.

 */

