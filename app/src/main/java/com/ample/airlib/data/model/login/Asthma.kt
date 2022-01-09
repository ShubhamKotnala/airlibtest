package com.ample.airlib.data.model.login

import com.google.gson.annotations.SerializedName


data class Asthma (@SerializedName("medications") var medications: ArrayList<Medications> = arrayListOf(),
                   @SerializedName("labs") var labs: ArrayList<Labs> = arrayListOf())