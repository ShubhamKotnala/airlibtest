package com.ample.airlib.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ample.airlib.data.HomeRepository
import com.ample.airlib.data.LoginDao
import com.ample.airlib.network.ApiService

class HomeViewModelFactory(private val apiHelper: ApiService, private val dao: LoginDao) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeRepository = HomeRepository(apiHelper, dao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}