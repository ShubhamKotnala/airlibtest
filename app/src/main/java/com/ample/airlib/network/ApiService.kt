package com.ample.airlib.network

import com.ample.airlib.data.model.login.LoginResponse
import retrofit2.http.GET

interface ApiService {
//https://run.mocky.io/v3/6708ec9f-af9d-4114-b729-3d452e84052f
    //https://run.mocky.io/v3/aa5c8992-d575-4577-95d1-3c1bbd21e9f4
    @GET("v3/aa5c8992-d575-4577-95d1-3c1bbd21e9f4")
    suspend fun loginUsers(): LoginResponse
}