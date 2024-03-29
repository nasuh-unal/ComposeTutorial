package com.example.producestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.producestate.ui.theme.ProduceStateTheme
import kotlinx.coroutines.delay
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProduceStateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyComposition()
                }
            }
        }
    }
}

@Composable
fun MyComposition() {

    var isLoading: Boolean by remember { mutableStateOf(true) }

    val data: String by produceState(initialValue = "") {
        value = getData()
        isLoading = false
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = data)
        }

    }
}

suspend fun getData(): String {
    delay(2000) // simulate a long-running operation
    return "Data loaded from a data source"
}

/*
    - produceState ise, Jetpack Compose'da bir Composable'in state'ini
    otomatik olarak güncellemesi için kullanılan bir fonksiyondur.

    - produceState, state'in başlangıç değerini ve bir suspend fonksiyonu alır.
    suspend fonksiyonu, state'in güncellenmesi gerektiği zamanlarda çalıştırılabilir.

    -Suspend fun bir değer dönderdiğinde state kendisini günceller
 */