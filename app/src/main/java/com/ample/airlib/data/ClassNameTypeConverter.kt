package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.AssociatedDrug
import com.ample.airlib.data.model.login.ClassName
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ClassNameTypeConverter {
    @TypeConverter
    fun fromListToString(list: List<ClassName>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<ClassName>? =
        Gson().fromJson(string, object : TypeToken<List<ClassName>>(){}.type)
}