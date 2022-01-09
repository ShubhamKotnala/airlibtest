package com.example.example

import com.ample.airlib.data.model.login.Problems
import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

  @SerializedName("problems" ) var problems : ArrayList<Problems> = arrayListOf()

)