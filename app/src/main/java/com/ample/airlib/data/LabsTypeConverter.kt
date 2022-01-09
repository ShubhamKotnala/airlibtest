package com.ample.airlib.data

import androidx.room.TypeConverter
import com.ample.airlib.data.model.login.Labs
import com.ample.airlib.data.model.login.Medications
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LabsTypeConverter {
    @TypeConverter
    fun fromListToString(list: List<Labs>) : String? = Gson().toJson(list)

    @TypeConverter
    fun fromStringToList(string: String): List<Labs>? =
        Gson().fromJson(string, object : TypeToken<List<Labs>>(){}.type)
}