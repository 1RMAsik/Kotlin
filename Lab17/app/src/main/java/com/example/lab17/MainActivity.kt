package com.example.lab17

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class Student(val name: String, var course: Int, var status: String)

class StudentAdapter(private val students: List<Student>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val courseTextView: TextView = itemView.findViewById(R.id.courseTextView)
        val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
        val promoteButton: Button = itemView.findViewById(R.id.promoteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.nameTextView.text = "Имя: ${student.name}"
        holder.courseTextView.text = "Курс: ${student.course}"
        holder.statusTextView.text = "Статус: ${student.status}"

        holder.promoteButton.setOnClickListener {
            if (student.course < 4) {
                student.course++
                holder.courseTextView.text = "Курс: ${student.course}"
            } else if (student.course == 4) {
                student.status = "Выпускник"
                holder.statusTextView.text = "Статус: ${student.status}"
            }
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val students = listOf(
            Student("Иванов", 1, "Учащийся"),
            Student("Петров", 2, "Учащийся"),
            Student("Сидоров", 3, "Учащийся"),
            // Добавьте других студентов по мере необходимости
        )

        val adapter = StudentAdapter(students)
        recyclerView.adapter = adapter
    }
}