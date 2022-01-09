package com.ample.airlib.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ample.airlib.data.HomeRepository
import com.ample.airlib.data.model.login.LoginResponse
import com.ample.airlib.utils.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getHomeData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = homeRepository.getLoginData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}