package com.kristiania.lecture.databasedemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kristiania.lecture.databasedemo.entities.Student

const val DATABASE_NAME: String = "students_database"

@Database(entities = [Student::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun getStudentDAO(): StudentDAO


    companion object {
        private var db: DataBase? = null

        // Get the singleton instance of Database object.
        fun getDatabase(context: Context): DataBase {
            val newDb =
                db ?: Room.databaseBuilder(context, DataBase::class.java, DATABASE_NAME).build()
            return newDb.also {
                db = it
            }
        }

    }
}