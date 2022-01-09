package com.ample.airlib.data

import com.ample.airlib.data.model.LoggedInUser
import com.ample.airlib.data.model.login.LoginResponse
import com.ample.airlib.network.ApiService
import java.io.IOException

class LoginRepository(val apiService: ApiService, val dao: LoginDao) {

    suspend fun getLoginData(username: String, password: String) : LoginResponse {
        return apiService.loginUsers()
    }

    suspend fun insert(word: LoginResponse) {
        dao.insert(word)
    }
}