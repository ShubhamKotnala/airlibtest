package com.ample.airlib.data.model.login

import com.google.gson.annotations.SerializedName


data class Medications(
    @SerializedName("medicationsClasses") var medicationsClasses: ArrayList<MedicationsClasses> = arrayListOf(),
    @SerializedName("labs") var labs: ArrayList<Labs> = arrayListOf())