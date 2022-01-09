package com.ample.airlib.data

import com.ample.airlib.data.model.login.LoginResponse

class HomeRepository(val dataSource: HomeDataSource, val dao: LoginDao) {

    suspend fun getLoginData(): List<LoginResponse> {
            return dao.getLoginData()
    }
}