package com.kristiania.lecture.databasedemo.db

import androidx.room.*
import com.kristiania.lecture.databasedemo.entities.Student

@Dao
interface StudentDAO {

    @Insert
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * FROM student_table")
    suspend fun fetchAll(): List<Student>

    @Query("select * from student_table where id = :studentID")
    suspend fun getStudentWithID(studentID: Long): Student

}