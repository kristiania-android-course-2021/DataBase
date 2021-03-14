package com.kristiania.lecture.databasedemo.entities


data class Student(
    val id: Long = 0,
    val name: String,
    val course: String
)