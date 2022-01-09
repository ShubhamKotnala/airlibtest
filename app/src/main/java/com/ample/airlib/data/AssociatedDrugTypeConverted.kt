package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.AssociatedDrug
import com.ample.airlib.data.model.login.Asthma
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssociatedDrugTypeConverted {
    @TypeConverter
    fun fromListToString(list: List<AssociatedDrug>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<AssociatedDrug>? =
        Gson().fromJson(string, object : TypeToken<List<AssociatedDrug>>(){}.type)
}