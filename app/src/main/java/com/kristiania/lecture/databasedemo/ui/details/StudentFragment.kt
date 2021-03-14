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

        // Observers
        observeData()
        // View listeners
        initViewListeners()
    }

    private fun initViewListeners() {
        with(binding) {
            saveButton.setOnClickListener {
                // If student Id is null save the new data else update the record.
            }
        }
    }

    private fun observeData() {

        // Observe student live data

        // Observe the messages from view-model.

    }
}