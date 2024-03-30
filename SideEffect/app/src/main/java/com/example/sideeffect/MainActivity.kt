package com.example.sideeffect

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sideeffect.ui.theme.SideEffectTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Counter()
                }
            }
        }
    }
}

@Composable
fun Counter() {
    // Define a state variable for the count
    val count = remember { mutableStateOf(0) }

    // Use SideEffect to log the current value of count
    SideEffect {
        // Called on every recomposition
        println("Count is ${count.value}")
    }

    Column {
        Button(onClick = { count.value++ }) {
            // This recomposition doesn't trigger the outer side effect
            // every time button has been tapped
            Text("Increase Count ${count.value}")
        }
    }
}

/*
    - Yan etkinin yalnızca geçerli Composable fonksiyonu yeniden oluşturulduğunda
    tetiklendiğini ve iç içe geçmiş Composable fonksiyonları için tetiklenmediğini unutmayın.
    Bu, başka bir Composable fonksiyonunu çağıran bir Composable fonksiyonunuz varsa,
    iç Composable fonksiyonu yeniden oluşturulduğunda dış Composable fonksiyonundaki
    SideEffect'in tetiklenmeyeceği anlamına gelir.

    - Yani sideEffect bir bakıma kapsamlandırma işlemi de sağlar. İlk olarak yürütüldükten
    sonra doğrudan etkileşmli bir composable tetiklemez ise sideEffect çalışmaz.
 */


/*

    @Composable
    fun ListOfNames() {
        val nameList = remember { mutableStateOf(listOf<String>()) }
        nameList.value = fetchData()  // Newtowrk Call

        LazyColumn {
            items(nameList.value) { item ->
                Card(modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 5.dp)) {
                    Text(text = item, modifier = Modifier.padding(10.dp))
                }
            }
        }
    }


    - Compose state'ini compose tarafından yönetilmeyen nesnelerle paylaşmak için SideEffect
     composable kullanın. Bir SideEffect kullanmak, efektin her başarılı recompositiondan
      sonra yürütülmesini garanti eder.

    - Compose olmayan herhangi bir değer değiştirilirse, yeniden birleştirme
    gerçekleşmeyecektir.

    - fetchData() ağ çağrısı kullanıcı tarafından birden çok kez çağrılabilir, bu da bir
    seferde yüksek miktarda istek alarak sunucunun aşırı yüklenmesine neden olur

    - fetchData() ağ çağrısının yanıt vermesi veya sunucudan uygulamaya veri göndermesi
    daha uzun sürebilir

    - Bu sorunları çözmek için SideEffect, parent composable'ın recomposition
    olması API çağrıları, korutin yürütme vb. gibi asenkron işlemleri yürütür.

*/

