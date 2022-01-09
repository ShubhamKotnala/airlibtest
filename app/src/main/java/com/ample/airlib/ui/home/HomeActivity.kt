package com.ample.airlib.ui.home

import android.graphics.Color
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
import com.ample.airlib.network.RetrofitBuilder
import com.ample.airlib.utils.Status
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.ample.airlib.adapter.HomeAdapter
import com.ample.airlib.data.model.login.Problems
import com.ample.airlib.databinding.BottomSheetDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.DateFormat

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var dialog : BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialog)

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
        val adapter = HomeAdapter(list, ::showProblemDetails)
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

    private fun showProblemDetails(problems: Problems) {
        if (dialog.isShowing) dialog.cancel()

        val binding : BottomSheetDetailsBinding =
            BottomSheetDetailsBinding.inflate(layoutInflater, null, false)

        (binding.root as View).setBackgroundColor(Color.TRANSPARENT)

        dialog.setCancelable(true)
        dialog.setContentView(binding.root)

        binding.name.text = problems.name
        binding.dose1.text = problems.medications!!.medicationsClasses[0].className[0].associatedDrug[0].name
        binding.dose2.text = problems.medications!!.medicationsClasses[0].className[0].associatedDrug2[0].name
        binding.strength1.text = problems.medications!!.medicationsClasses[0].className[0].associatedDrug[0].strength
        binding.strength2.text = problems.medications!!.medicationsClasses[0].className[0].associatedDrug2[0].strength

        dialog.show()
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