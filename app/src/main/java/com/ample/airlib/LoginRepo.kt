package com.ample.airlib

import com.ample.airlib.network.ApiHelper

class LoginRepo(private val apiHelper: ApiHelper) {

    suspend fun getLoginData() = apiHelper.getLoginData()
}