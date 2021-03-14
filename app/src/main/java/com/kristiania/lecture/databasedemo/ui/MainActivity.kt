package com.kristiania.lecture.databasedemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.kristiania.lecture.databasedemo.R
import com.kristiania.lecture.databasedemo.databinding.ActivityMainBinding
import com.kristiania.lecture.databasedemo.ui.list.StudentsListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, StudentsListFragment.newInstance(), "StudentsFragment")
            .commit()
    }
}