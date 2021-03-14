package com.kristiania.lecture.databasedemo.db

import android.provider.BaseColumns
import androidx.room.*
import com.kristiania.lecture.databasedemo.entities.Student

// Mention table and column name

object StudentTable : BaseColumns {
    const val TABLE_NAME = "STUDENT_TABLE"
    const val COLUMN_ID = "ID"
    const val COLUMN_NAME = "NAME"
    const val COLUMN_COURSE = "COURSE"
}

@Dao
interface StudentDAO {

    @Insert
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM STUDENT_TABLE")
    fun fetchAllRecord(): List<Student>

    @Query("SELECT * FROM STUDENT_TABLE where ID = :studentID")
    fun getRecordWithID(studentID: Long): Student?

}
