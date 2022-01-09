package com.ample.airlib.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ample.airlib.data.LoginDao
import com.ample.airlib.data.LoginDataSource
import com.ample.airlib.data.LoginRepository
import com.ample.airlib.network.ApiHelper
import com.ample.airlib.network.ApiService

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(private val apiHelper: ApiService, private val dao: LoginDao) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepository = LoginRepository(
                    dataSource = LoginDataSource(apiHelper), dao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}