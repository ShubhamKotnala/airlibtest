package com.ample.airlib.data.model.login

import com.google.gson.annotations.SerializedName

data class ClassName(
    @SerializedName("associatedDrug") var associatedDrug: ArrayList<AssociatedDrug> = arrayListOf(),
    @SerializedName("associatedDrug#2") var associatedDrug2: ArrayList<AssociatedDrug2> = arrayListOf(),
    @SerializedName("className2") var className2: ArrayList<ClassName2> = arrayListOf()
)