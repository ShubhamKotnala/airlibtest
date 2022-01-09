package com.ample.airlib.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ample.airlib.R
import com.ample.airlib.data.LoginDatabase
import com.ample.airlib.data.model.login.LoginResponse
import com.ample.airlib.databinding.ActivityHomeBinding
import com.ample.airlib.databinding.ActivityLoginBinding
import com.ample.airlib.network.RetrofitBuilder
import com.ample.airlib.ui.login.LoginViewModelFactory
import com.ample.airlib.utils.Status
import java.text.SimpleDateFormat
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.ample.airlib.adapter.HomeAdapter
import com.ample.airlib.data.model.login.Problems
import java.text.DateFormat

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database by lazy { LoginDatabase.getDatabase(this) }

        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(RetrofitBuilder.apiService,  database.loginDao()))[HomeViewModel::class.java]

        getHomeData()
    }

    private fun getHomeData() {
        homeViewModel.getHomeData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data != null && resource.data.isNotEmpty())
                            setUiData(resource.data[0])
                        else
                            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun setUiData(loginResponse: LoginResponse) {
        binding.username.text = loginResponse.username.capitalized()
        showTime()

        setRecyclerView(loginResponse.problems)
    }

    private fun setRecyclerView(list : List<Problems>) {
        val adapter = HomeAdapter(list)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.rvData.layoutManager = layoutManager
        binding.rvData.adapter = adapter
    }

    private fun showTime() {
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                val currentDateTimeString: String = DateFormat.getDateTimeInstance().format(Date())
                binding.datetime.text = currentDateTimeString
                mainHandler.postDelayed(this, 1000)
            }
        })
    }
}

/**
 * Extension function to simplify capitalize 1st char of a string.
 */
fun String.capitalized(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}