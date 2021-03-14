package com.kristiania.lecture.databasedemo.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kristiania.lecture.databasedemo.R
import com.kristiania.lecture.databasedemo.databinding.StudentsListFragmentBinding
import com.kristiania.lecture.databasedemo.ui.details.StudentFragment

class StudentsListFragment : Fragment(R.layout.students_list_fragment) {

    companion object {
        fun newInstance() = StudentsListFragment()
    }

    private lateinit var adapter: StudentsAdapter
    private lateinit var binding: StudentsListFragmentBinding
    private val viewModel: StudentsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init
        binding = StudentsListFragmentBinding.bind(view)
        // Init view model

        // Configure List
        configureList()
        // Observe view model
        observers()
        // Listeners for view events
        viewListeners()
    }

    private fun viewListeners() {
        binding.addButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    StudentFragment.newInstance()
                ).addToBackStack("StudentFragment")
                .commit()
        }
    }

    private fun observers() {
        // observe student list live data from view model.
    }

    private fun configureList() {
        adapter = StudentsAdapter { student ->
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    StudentFragment.newInstance(studentID = student.id)
                ).addToBackStack("StudentFragment")
                .commit()
        }

        with(binding.studentsList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }
    }
}