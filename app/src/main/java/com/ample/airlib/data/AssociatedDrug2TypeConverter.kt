package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.AssociatedDrug2
import com.ample.airlib.data.model.login.ClassName
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssociatedDrug2TypeConverter {
    @TypeConverter
    fun fromListToString(list: List<AssociatedDrug2>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<AssociatedDrug2>? =
        Gson().fromJson(string, object : TypeToken<List<AssociatedDrug2>>(){}.type)
}