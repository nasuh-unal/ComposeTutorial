package com.example.rememberupdatedstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.rememberupdatedstate.ui.theme.RememberUpdatedStateTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RememberUpdatedStateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Header()
                }
            }
        }
    }
}

@Composable
fun Header() {
    var caller by remember { mutableStateOf("Someone") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.clickable { caller = "Younes" },
            text = "Hello, $caller",
            color= Color.Green,
            fontSize = 32.sp
        )
        Text(
            modifier = Modifier.clickable { caller = "Fouad" },
            text = "Hello, $caller",
            color= Color.Green,
            fontSize = 32.sp
        )
        CallTimer(caller)
    }
}

@Composable
fun CallTimer(caller: String) {
    println("From the Composable: $caller")
    val updated by rememberUpdatedState(newValue =caller )
    LaunchedEffect(key1 = Unit) {
        delay(5000)
        println("From the LaunchedEffect: $updated")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RememberUpdatedStateTheme {
        Header()
    }
}