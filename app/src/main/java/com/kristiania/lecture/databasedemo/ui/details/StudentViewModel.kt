package com.kristiania.lecture.databasedemo.ui.details

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kristiania.lecture.databasedemo.db.DataBase
import com.kristiania.lecture.databasedemo.db.StudentDAO
import com.kristiania.lecture.databasedemo.entities.Student
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {

    private lateinit var studentDao: StudentDAO

    private val _studentLiveData: MutableLiveData<Student> = MutableLiveData()
    val studentLiveData: LiveData<Student> = _studentLiveData

    fun init(context: Context, studentID: Long?) {
        // Initialize database
        studentDao = DataBase.getDatabase(context).getStudentDAO()
        if (studentID != null) {
            getStudent(studentID)
        }
    }

    private fun getStudent(studentID: Long) {
        viewModelScope.launch {
            _studentLiveData.value = studentDao.getStudentWithID(studentID)
        }
    }

    fun saveData(name: String?, course: String?) {
        viewModelScope.launch {
            if (!name.isNullOrEmpty() && !course.isNullOrEmpty()) {
                try {
                    studentDao.insert(Student(name = name, course = course))
                } catch (e: Exception) {
                    e.fillInStackTrace()
                }
            }
        }

    }

    fun updateData(id: Long, name: String?, course: String?) {
        viewModelScope.launch {
            if (!name.isNullOrEmpty() && !course.isNullOrEmpty()) {
                studentDao.update(Student(id, name, course))
                Log.d("", "Updated")
            }
        }

    }


}