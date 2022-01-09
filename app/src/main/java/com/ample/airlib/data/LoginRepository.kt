package com.ample.airlib.data

import com.ample.airlib.data.model.LoggedInUser
import com.ample.airlib.data.model.login.LoginResponse

class LoginRepository(val dataSource: LoginDataSource, val dao: LoginDao) {

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    suspend fun getLoginData(username: String, password: String) : LoginResponse {
        return dataSource.getLoginData(username, password)
    }

    suspend fun insert(word: LoginResponse) {
        dao.insert(word)
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}