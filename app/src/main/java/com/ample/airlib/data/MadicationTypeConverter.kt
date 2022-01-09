package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.Asthma
import com.ample.airlib.data.model.login.Medications
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MadicationTypeConverter {
    @TypeConverter
    fun fromListToString(list: List<Medications>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<Medications>? =
        Gson().fromJson(string, object : TypeToken<List<Medications>>(){}.type)
}