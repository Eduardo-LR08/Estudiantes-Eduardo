package com.example.estudianteseduardo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.estudianteseduardo.Data.ListStudents
import com.example.estudianteseduardo.Tools.Constants
import com.example.estudianteseduardo.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListBinding
    private val listStudents= ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_list)
        listStudents.showListStudents()

        val adapter = ArrayAdapter<String>(this@ListActivity,android.R.layout.simple_list_item_1,listStudents.getStringArray())
        binding.ltvStudents.adapter = adapter

        binding.ltvStudents.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View,position: Int, id: Long ->

            val selectedItem= adapterView.getItemAtPosition(position)

            Toast.makeText(this@ListActivity,"$position $id $selectedItem",Toast.LENGTH_SHORT).show()

            val intent = Intent(this@ListActivity,DetailActivity::class.java).apply{

                putExtra(Constants.ID,position)
            }
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        val adapter = ArrayAdapter<String>(this@ListActivity,android.R.layout.simple_list_item_1,listStudents.getStringArray())
        binding.ltvStudents.adapter = adapter
    }
}