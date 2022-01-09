package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.Asthma
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AshtamaTypeConverter {
    @TypeConverter
    fun fromListToString(list: List<Asthma>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<Asthma>? =
        Gson().fromJson(string, object : TypeToken<List<Asthma>>(){}.type)
}