package com.tenz.login.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tenz.login.db.dao.UserDao
import com.tenz.login.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private const val DB_NAME = "tweather_login.db"

        private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return instance ?: Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .build().also {
                instance = it
            }
        }

    }

}