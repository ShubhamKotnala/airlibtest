package com.ample.airlib.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ample.airlib.data.LoginRepository
import com.ample.airlib.data.model.login.LoginResponse
import com.ample.airlib.utils.Resource
import com.ample.airlib.utils.Status
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    var _loginForm = MutableLiveData<Resource<Any>>()

    fun callLogin(username: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = loginRepository.getLoginData(username, password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insert(word: LoginResponse) = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = loginRepository.insert(word)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = Resource.error(data = Status.ERROR, message =  "Not a valid username")
        } else if (!isPasswordValid(password)) {
            _loginForm.value = Resource.error(data = Status.ERROR, message =  "Password must be >5 characters")
        } else {
            _loginForm.value = Resource.success(data = Status.SUCCESS)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}