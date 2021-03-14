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

    private val adapter = StudentsAdapter { student ->
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                StudentFragment.newInstance(studentID = student.id)
            ).addToBackStack("StudentFragment")
            .commit()
    }
    private lateinit var binding: StudentsListFragmentBinding
    private val viewModel: StudentsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init
        binding = StudentsListFragmentBinding.bind(view)
        // Init view model
        viewModel.init(requireContext())

        configureList()
        observe()
        // Listeners for view events
        viewListeners()
    }

    private fun observe() {
        viewModel.studentsListLiveData.observe(viewLifecycleOwner) {
            adapter.setStudentsList(it)
        }
    }

    private fun configureList() {
        binding.studentsList.layoutManager = LinearLayoutManager(requireContext())
        binding.studentsList.adapter = adapter
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
}