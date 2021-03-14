package com.kristiania.lecture.databasedemo.ui.details

import android.content.Context
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {

    fun init(context: Context, studentID: Long?) {
        // Initialize database

        // if student id available then fetch the data for update.
    }

    private fun fetchData(studentID: Long) {

    }

    fun saveData(name: String?, course: String?) {

    }

    fun updateData(id: Long, name: String?, course: String?) {

    }


}