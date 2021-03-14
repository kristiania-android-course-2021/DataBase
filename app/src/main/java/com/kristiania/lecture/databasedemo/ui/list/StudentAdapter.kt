package com.kristiania.lecture.databasedemo.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kristiania.lecture.databasedemo.databinding.StudentItemBinding
import com.kristiania.lecture.databasedemo.entities.Student
class StudentsAdapter(val lambdaFunction: (Student) -> Unit) :
    RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    private val studentsList = mutableListOf<Student>()

    class ViewHolder(val binding: StudentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.studentName.text = student.name
            binding.studentCourse.text = student.course
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(StudentItemBinding.inflate(LayoutInflater.from(parent.context)))
        holder.itemView.setOnClickListener {
            lambdaFunction(studentsList[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return studentsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(studentsList[position])
    }

    fun setStudentsList(list: List<Student>) {
        studentsList.clear()
        studentsList.addAll(list)
        notifyDataSetChanged()
    }

}