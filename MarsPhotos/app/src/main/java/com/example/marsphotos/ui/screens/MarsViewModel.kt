package com.example.marsphotos.ui.screens
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            /*
            ViewModel'in veriler için doğrudan ağ isteği yapması yerine, repository verileri sağlar.
            ViewModel artık doğrudan MarsApi koduna başvurmaz.
            Bu yaklaşım, verileri alan kodun ViewModel'den bağımsız olmasına yardımcı olur.
            Depo getMarsPhotos() adında bir fonksiyona sahip olduğu sürece, gevşek bağlı olmak
            ViewModel veya depoda diğerini olumsuz etkilemeden değişiklik yapılmasına olanak tanır.
             */
            marsUiState = MarsUiState.Loading
            delay(2000)
            marsUiState = try {
                //val marsPhotosRepository = NetworkMarsPhotosRepository() constructor'a yazdığımız için bunu kaldırdık
                val result = marsPhotosRepository.getMarsPhotos()
                //val listResult = MarsApi.retrofitService.getPhotos()
                MarsUiState.Success(/*"Success: ${result.imgSrc} Mars photos retrieved"*/ result)
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
    //ViewModel'e bir parametre aktarmak istediğimizde viewModelFactory yardımı alarak parametre aktarabiliriz
    //Componaion object bir sınıfın nesnesini oluşturmadan ona erişim sağlamamızı sağlar
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}
