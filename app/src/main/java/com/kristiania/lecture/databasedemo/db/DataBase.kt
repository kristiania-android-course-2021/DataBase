package com.kristiania.lecture.databasedemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kristiania.lecture.databasedemo.entities.Student

val DATABASE_NAME: String = "students_database"

@Database(entities = [Student::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun studentDAO(): StudentDAO

    //Singleton db object
    companion object {
        var db: DataBase? = null

        fun getDB(context: Context): DataBase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    DataBase::class.java, DATABASE_NAME
                ).build()
            }
            return db!!
        }
    }
}