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

    private lateinit var binding: StudentsListFragmentBinding
    private val viewModel: StudentsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StudentsListFragmentBinding.bind(view)
        viewModel.init(requireContext())
        val adapter = StudentsAdapter { student ->
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    StudentFragment.newInstance(studentID = student.id)
                ).addToBackStack("StudentFragment")
                .commit()
        }
        binding.studentsList.layoutManager = LinearLayoutManager(requireContext())
        binding.studentsList.adapter = adapter
        viewModel.studentsLiveData.observe(viewLifecycleOwner) {
            adapter.setStudentsList(it)
        }

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