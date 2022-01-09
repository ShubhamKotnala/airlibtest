package com.ample.airlib.data

import com.ample.airlib.data.model.LoggedInUser
import com.ample.airlib.data.model.login.LoginResponse
import com.ample.airlib.network.ApiService
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(private val apiService: ApiService) {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    suspend fun getLoginData(username: String, password: String) : LoginResponse {
       return apiService.loginUsers()
    }

    fun logout() {
        // TODO: revoke authentication
    }
}