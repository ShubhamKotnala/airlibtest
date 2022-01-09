package com.ample.airlib.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ample.airlib.R
import com.ample.airlib.data.LoginDatabase
import com.ample.airlib.databinding.ActivitySplashBinding
import com.ample.airlib.network.RetrofitBuilder
import com.ample.airlib.ui.home.HomeActivity
import com.ample.airlib.ui.home.HomeViewModel
import com.ample.airlib.ui.home.HomeViewModelFactory
import com.ample.airlib.ui.login.LoginActivity
import com.ample.airlib.utils.Status

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: HomeViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database by lazy { LoginDatabase.getDatabase(this) }

        splashViewModel = ViewModelProvider(this, HomeViewModelFactory(RetrofitBuilder.apiService,  database.loginDao()))[HomeViewModel::class.java]

        Handler(Looper.getMainLooper()).postDelayed({ redirectToNextScreen() },2000)
    }

    private fun redirectToNextScreen() {
        splashViewModel.getHomeData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data == null || resource.data.isEmpty())
                            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        else
                            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))

                        finish()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        })
    }
}