package com.example.launcheffect

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.launcheffect.ui.theme.LaunchEffectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchEffectTheme {
                // A surface container using the 'background' color from the theme
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
    var stateFirst by remember { mutableStateOf(true) }
    var stateSecond by remember { mutableStateOf(true) }
    val context = LocalContext.current

    /*LaunchedEffect(key1 = stateFirst) {
        Toast.makeText(context, "Hello World", Toast.LENGTH_LONG).show()
    }*/

    Toast.makeText(LocalContext.current, "Hello World", Toast.LENGTH_LONG).show()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.clickable { stateFirst = !stateFirst },
            text = "First Text clicked $stateFirst",
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.clickable { stateSecond = !stateSecond },
            text = "Second Text clicked $stateSecond",
            fontSize = 32.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LaunchEffectTheme {
        Header()
    }
}