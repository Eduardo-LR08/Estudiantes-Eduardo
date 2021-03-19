package com.example.estudianteseduardo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.estudianteseduardo.Data.ListStudents
import com.example.estudianteseduardo.Tools.Constants
import com.example.estudianteseduardo.databinding.ActivityEditarEliminarBinding
import com.example.estudianteseduardo.databinding.ActivityListBinding

class EditarEliminarActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditarEliminarBinding
    private val listStudents= ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditarEliminarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_EditarEliminar)
        listStudents.showListStudents()

        val adapter = ArrayAdapter<String>(this@EditarEliminarActivity,android.R.layout.simple_list_item_1,listStudents.getStringArray())
        binding.ltvStudents.adapter = adapter
        miDialogo().show()

    }

    private fun miDialogo(): AlertDialog {
        val miAlerta= AlertDialog.Builder(this@EditarEliminarActivity)

        miAlerta.setTitle("Editar o eliminar ")
        miAlerta.setMessage("Estudiante")
        miAlerta.setPositiveButton("editar"){_,_ ->
            binding.ltvStudents.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View,position: Int, id: Long ->

                val selectedItem= adapterView.getItemAtPosition(position)

                //Toast.makeText(this@EditarEliminarActivity,"$position $id $selectedItem",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@EditarEliminarActivity,EditarActivity::class.java).apply{

                    putExtra(Constants.ID,position)

                }
                startActivity(intent)
            }

        }
        miAlerta.setNegativeButton("eliminar"){_,_ ->
          /*  if(listStudents.delete(student.name))
            {
                Toast.makeText(this@EditarEliminarActivity,"Elemento eliminado",Toast.LENGTH_SHORT).show()

            }
            else {
                Toast.makeText(this@EditarEliminarActivity,"Elemento No eliminado",Toast.LENGTH_SHORT).show()
            }*/

        }
        return miAlerta.create()

    }

    override fun onRestart() {
        super.onRestart()
        val adapter = ArrayAdapter<String>(this@EditarEliminarActivity,android.R.layout.simple_list_item_1,listStudents.getStringArray())
        binding.ltvStudents.adapter = adapter
    }
}