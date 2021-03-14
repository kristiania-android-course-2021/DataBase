package com.kristiania.lecture.databasedemo.ui.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kristiania.lecture.databasedemo.db.DataBase
import com.kristiania.lecture.databasedemo.db.StudentDAO
import com.kristiania.lecture.databasedemo.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private lateinit var studentDAO: StudentDAO

    private val _studentLiveData: MutableLiveData<Student> = MutableLiveData()
    val studentLiveData: LiveData<Student> = _studentLiveData

    private val _messageLiveData: MutableLiveData<String> = MutableLiveData()
    val messageLiveData: LiveData<String> = _messageLiveData

    fun init(context: Context, studentID: Long?) {
        studentDAO = DataBase.getDB(context).studentDAO()
        if (studentID != null) {
            fetchData(studentID)
        }
    }

    private fun fetchData(studentID: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val student = studentDAO.getRecordWithID(studentID)
            if (student != null) {
                _studentLiveData.postValue(student)
            } else {
                _messageLiveData.postValue("That student record is not available")
            }
        }
    }

    fun saveData(id: Long?, name: String?, course: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!name.isNullOrBlank() && !course.isNullOrBlank()) {
                try {
                    if (id == null) {
                        studentDAO.insert(Student(name = name, course = course))
                    } else {
                        studentDAO.update(Student(id = id, name = name, course = course))
                    }
                } catch (e: Exception) {
                    _messageLiveData.postValue(e.message)
                }
            } else {
                _messageLiveData.postValue("Invalid data. Name and course cannot be empty")
            }
        }
    }


}