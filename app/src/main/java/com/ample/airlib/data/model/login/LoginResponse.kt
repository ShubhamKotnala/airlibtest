package com.ample.airlib.data.model.login

import androidx.room.*
import com.ample.airlib.data.ProblemsTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "problems")
data class LoginResponse (@ColumnInfo(name = "username")var username : String,
                          @PrimaryKey
                          @TypeConverters(ProblemsTypeConverter::class)
                          @SerializedName("problems") var problems : ArrayList<Problems> = arrayListOf())

data class Problems(
    @SerializedName("name") var name: String? = null,
    @SerializedName("medications") var medications: Medications? = Medications())

data class Medications(
    @SerializedName("medicationsClasses") var medicationsClasses: ArrayList<MedicationsClasses> = arrayListOf(),
    @SerializedName("labs") var labs: ArrayList<Labs> = arrayListOf())

data class Labs (@SerializedName("missing_field") var missingField : String? = null)


data class MedicationsClasses(
    @SerializedName("className") var className: ArrayList<ClassName> = arrayListOf())

data class ClassName(
    @SerializedName("associatedDrug") var associatedDrug: ArrayList<AssociatedDrug> = arrayListOf(),
    @SerializedName("associatedDrug#2") var associatedDrug2: ArrayList<AssociatedDrug2> = arrayListOf(),
    @SerializedName("className2") var className2: ArrayList<ClassName2> = arrayListOf())

data class AssociatedDrug(
    @SerializedName("name") var name: String? = null,
    @SerializedName("dose") var dose: String? = null,
    @SerializedName("strength") var strength: String? = null)

data class AssociatedDrug2(
    @SerializedName("name") var name: String? = null,
    @SerializedName("dose") var dose: String? = null,
    @SerializedName("strength") var strength: String? = null)

data class ClassName2(
    @SerializedName("associatedDrug") var associatedDrug: ArrayList<AssociatedDrug> = arrayListOf(),
    @SerializedName("associatedDrug#2") var associatedDrug2 : ArrayList<AssociatedDrug2> = arrayListOf())