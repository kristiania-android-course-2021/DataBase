package com.kristiania.lecture.databasedemo.db

import androidx.room.*
import com.kristiania.lecture.databasedemo.entities.Student

@Dao
interface StudentDAO {

    @Insert
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM student_table")
    fun fetchAll(): List<Student>

    @Query("select * from student_table where id = :studentID")
    fun getStudentWithID(studentID: Long): Student

}