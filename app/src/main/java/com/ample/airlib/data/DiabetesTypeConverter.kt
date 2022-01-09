package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.Diabetes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DiabetesTypeConverter {
    @TypeConverter
    fun fromListToString(list: List<Diabetes>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<Diabetes>? =
        Gson().fromJson(string, object : TypeToken<List<Diabetes>>(){}.type)
}