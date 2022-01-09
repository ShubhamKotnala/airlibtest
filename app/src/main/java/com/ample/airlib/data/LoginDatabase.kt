package com.ample.airlib.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ample.airlib.data.model.login.LoginResponse

@Database(entities = arrayOf(LoginResponse::class), version = 1, exportSchema = false)
@TypeConverters(*[ProblemsTypeConverter::class])
abstract class LoginDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    companion object {
        // same time.
        const val DB_NAME = "airlib_database"
        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDatabase(context: Context): LoginDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoginDatabase::class.java,
                    DB_NAME).build()
                INSTANCE = instance
                instance
            }
        }
    }
}