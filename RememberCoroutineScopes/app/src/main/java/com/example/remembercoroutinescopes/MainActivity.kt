package com.example.remembercoroutinescopes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remembercoroutinescopes.ui.theme.RememberCoroutineScopesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RememberCoroutineScopesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    Header(scope)
                }
            }
        }
    }
}

@Composable
fun Header(scope: CoroutineScope) {
    var state by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                scope.launch {
                    delay(3000)
                    Toast.makeText(context, "Hello world $state", Toast.LENGTH_LONG).show()
                }
            },
            text = "Hello world $state",
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.clickable {
                state = !state
            },
            text = "Hello amigo $state",
            fontSize = 32.sp
        )
    }
}

/*
    RememberCoroutineScope scope içerisindeki kodları bloklamadan işlemleri yürütür
    Bu scope bir kez tetiklenndiğinde diğer uı değişikliğinden etkilenmeden bağımsız çalışır
    Aynı zamanda bir coroutine'i dışarıdan alabiliriz
 */