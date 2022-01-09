package com.ample.airlib.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getLoginData() = apiService.loginUsers()
}