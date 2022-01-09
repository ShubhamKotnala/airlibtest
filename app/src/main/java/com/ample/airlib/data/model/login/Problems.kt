package com.ample.airlib.data.model.login

import com.google.gson.annotations.SerializedName

data class Problems(
    @SerializedName("name") var name: String? = null,
    @SerializedName("medications") var medications: Medications? = Medications()

)