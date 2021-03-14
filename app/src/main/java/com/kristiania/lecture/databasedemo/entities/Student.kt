package com.kristiania.lecture.databasedemo.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kristiania.lecture.databasedemo.db.StudentTable

@Entity(tableName = StudentTable.TABLE_NAME)
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = StudentTable.COLUMN_ID)
    val id: Long = 0,
    @ColumnInfo(name = StudentTable.COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = StudentTable.COLUMN_COURSE)
    val course: String
)