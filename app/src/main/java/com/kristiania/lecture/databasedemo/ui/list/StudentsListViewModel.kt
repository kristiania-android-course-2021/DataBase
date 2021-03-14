package com.kristiania.lecture.databasedemo.ui.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kristiania.lecture.databasedemo.db.DataBase
import com.kristiania.lecture.databasedemo.db.StudentDAO
import com.kristiania.lecture.databasedemo.entities.Student
import kotlinx.coroutines.launch

class StudentsListViewModel : ViewModel() {

    private lateinit var studentDao: StudentDAO

    private val _studentsListLiveData: MutableLiveData<List<Student>> = MutableLiveData()
    val studentsListLiveData: LiveData<List<Student>> = _studentsListLiveData


    fun init(context: Context) {
        // Initialize the database
        studentDao = DataBase.getDatabase(context).getStudentDAO()
        // fetch the list of students available in database
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _studentsListLiveData.value = studentDao.fetchAll()
        }
    }
}