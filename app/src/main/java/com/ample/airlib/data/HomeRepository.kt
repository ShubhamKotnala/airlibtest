package com.ample.airlib.data

import com.ample.airlib.data.model.login.LoginResponse
import com.ample.airlib.network.ApiService

class HomeRepository(val dataSource: ApiService, val dao: LoginDao) {

    suspend fun getLoginData(): List<LoginResponse> {
            return dao.getLoginData()
    }
}