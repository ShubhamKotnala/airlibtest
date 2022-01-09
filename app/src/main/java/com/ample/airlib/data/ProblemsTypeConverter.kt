package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.Problems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProblemsTypeConverter {
    @TypeConverter
    fun fromListToString(list: ArrayList<Problems>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): ArrayList<Problems>? =
        Gson().fromJson(string, object : TypeToken<ArrayList<Problems>>(){}.type)
}