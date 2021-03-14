package com.kristiania.lecture.databasedemo.ui.details

import android.os.Bundle
import android.view.View
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
        // Init
        binding = StudentFragmentBinding.bind(view)
        studentID = arguments?.getLong("studentID")
        // Init view model
        viewModel.init(requireContext(), studentID)
        // View listeners
        initViewListeners()

        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                name.setText(it.name)
                course.setText(it.course)
                saveButton.setText(R.string.update)
            }
        }
    }

    private fun initViewListeners() {
        with(binding) {
            saveButton.setOnClickListener {
                if (studentID == null) {
                    viewModel.saveData(name.text.toString(), course.text.toString())
                } else {
                    viewModel.updateData(studentID!!, name.text.toString(), course.text.toString())
                }
            }
        }
    }
}