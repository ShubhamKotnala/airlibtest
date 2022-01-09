package com.ample.airlib.data.model.login

import com.google.gson.annotations.SerializedName


data class AssociatedDrug(
    @SerializedName("name") var name: String? = null,
    @SerializedName("dose") var dose: String? = null,
    @SerializedName("strength") var strength: String? = null

)