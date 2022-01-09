package com.ample.airlib.data.model.login

import androidx.room.*
import com.ample.airlib.data.ProblemsTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "problems")
data class LoginResponse (@ColumnInfo(name = "username")var username : String,
                          @PrimaryKey
                          @TypeConverters(ProblemsTypeConverter::class)
                          @SerializedName("problems") var problems : ArrayList<Problems> = arrayListOf()
)