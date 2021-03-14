package com.kristiania.lecture.databasedemo.ui.list

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

class StudentsListViewModel : ViewModel() {
    private lateinit var studentDAO: StudentDAO

    private val _studentsLiveData: MutableLiveData<List<Student>> = MutableLiveData()
    val studentsLiveData: LiveData<List<Student>> = _studentsLiveData

    fun init(context: Context) {
        studentDAO = DataBase.getDB(context).studentDAO()
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _studentsLiveData.postValue(studentDAO.fetchAllRecord())
        }
    }
}