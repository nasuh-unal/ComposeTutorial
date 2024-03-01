package com.example.marsphotos.data
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}
class NetworkMarsPhotosRepository (private val marsApiService: MarsApiService):MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        //return MarsApi.retrofitService.getPhotos() bu satırı eskiden viewModelde yazıyorduk
        return marsApiService.getPhotos()
    }
}