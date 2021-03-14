package com.kristiania.lecture.databasedemo.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kristiania.lecture.databasedemo.R
import com.kristiania.lecture.databasedemo.databinding.StudentFragmentBinding

class StudentFragment : Fragment(R.layout.student_fragment) {

    private var studentID: Long? = null
    private lateinit var binding: StudentFragmentBinding
    private val viewModel: StudentViewModel by viewModels()

    companion object {
        fun newInstance(studentID: Long? = null) = StudentFragment().apply {
            if (studentID != null) {
                arguments = Bundle().apply {
                    putLong("studentID", studentID)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StudentFragmentBinding.bind(view)
        studentID = arguments?.getLong("studentID")
        viewModel.init(requireContext(), studentID)
        observeData()
        initViewListeners()
    }

    private fun initViewListeners() {
        with(binding) {
            saveButton.setOnClickListener {
                viewModel.saveData(
                    id = studentID,
                    name = name.text?.toString(),
                    course = course.text?.toString()
                )
            }
        }
    }

    private fun observeData() {
        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                saveButton.setText(R.string.update)
                name.setText(it.name)
                course.setText(it.course)
            }
        }

        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}