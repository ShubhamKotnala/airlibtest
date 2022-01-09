package com.ample.airlib.data.model.login

import com.google.gson.annotations.SerializedName


data class MedicationsClasses(
    @SerializedName("className") var className: ArrayList<ClassName> = arrayListOf()
)