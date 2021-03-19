package com.example.estudianteseduardo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.estudianteseduardo.Adapters.StudentAdapter
import com.example.estudianteseduardo.Data.ListStudents
import com.example.estudianteseduardo.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerBinding
    private val listStudents=ListStudents()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_recycler)

        val list=listStudents.getEntityStudentArray()
        val adapter = StudentAdapter(list,this@RecyclerActivity)
        val linearLayout = LinearLayoutManager(this@RecyclerActivity,LinearLayoutManager.VERTICAL,false)

        binding.rwStudents.layoutManager = linearLayout
        binding.rwStudents.setHasFixedSize(true)
        binding.rwStudents.adapter= adapter
    }
}