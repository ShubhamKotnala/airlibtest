package com.ample.airlib.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ample.airlib.data.model.login.LoginResponse

@Dao
interface LoginDao {
    @Query("SELECT * FROM problems")
    suspend fun getLoginData(): List<LoginResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(loginResponse: LoginResponse)

    @Query("DELETE FROM problems")
    suspend fun deleteAll()
}