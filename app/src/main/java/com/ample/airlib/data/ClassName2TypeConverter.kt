package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.ClassName2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ClassName2TypeConverter {
    @TypeConverter
    fun fromListToString(list: List<ClassName2>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<ClassName2>? =
        Gson().fromJson(string, object : TypeToken<List<ClassName2>>(){}.type)
}