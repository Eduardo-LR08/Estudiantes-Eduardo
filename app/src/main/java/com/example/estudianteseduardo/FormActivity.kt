package com.example.estudianteseduardo

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import com.example.estudianteseduardo.Data.ListStudents
import com.example.estudianteseduardo.Entity.EntityStudent
import com.example.estudianteseduardo.databinding.ActivityFormBinding
import com.google.android.material.snackbar.Snackbar

class FormActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormBinding
    private var listStudents=ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_home)

        binding.btnOk.setOnClickListener{


            if (binding.edtName.text.isNotEmpty() && binding.edtLastName.text.isNotEmpty()) {

                //Toast.makeText(this@FormActivity,"estoy en evento clic",Toast.LENGTH_SHORT).show()
                val student = EntityStudent()
                student.name = binding.edtName.text.toString()
                student.lastName = binding.edtLastName.text.toString()
                student.gender = binding.spnGender.selectedItemPosition
                //val genderText:String = binding.spnGender.selectedItem.toString()
                when (binding.rgdDegree.checkedRadioButtonId) {
                    binding.rdbUnfinishStudents.id -> {
                        student.degree = "Trunco"
                    }
                    binding.rdbUniversityInten.id -> {
                        student.degree = "Pasante"
                    }
                    binding.rdbFinishedStudies.id -> {
                        student.degree = "Titulado"
                    }
                }

                student.sport = binding.ckbSport.isChecked
                student.travel = binding.ckbTravel.isChecked
                student.read = binding.ckbRead.isChecked

                student.financialAssistance = binding.swtFinancialAssistance.isChecked

                val index = listStudents.add(student)
                if (index >= 0) {

                    Toast.makeText(this@FormActivity, "Estudiante guardado.", Toast.LENGTH_SHORT).show()
                    binding.edtName.text.clear()
                    binding.edtLastName.text.clear()
                    binding.rgdDegree.clearCheck()
                    binding.spnGender.setSelection(0)
                    binding.ckbSport.isChecked=false
                    binding.ckbTravel.isChecked=false
                    binding.ckbRead.isChecked=false
                    binding.swtFinancialAssistance.isChecked=false

                }
                else {
                    Snackbar.make(it, "Estudiante No Guardado.", Snackbar.LENGTH_SHORT).show()
                }
            }
            else
            {
                Snackbar.make(it,"El nombre y los apellidos son obligatorios",Snackbar.LENGTH_SHORT).show()
            }


        }
        binding.edtDate.setOnClickListener{
            var year=2021
            var month=0
            var day=16

            var dpd = DatePickerDialog(this@FormActivity,DatePickerDialog.OnDateSetListener { view, y, m, d ->

            },year,month,day)
            dpd.show()
        }
/*
        binding.spnGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedText = parent?.getItemAtPosition(position)
                Toast.makeText(this@FormActivity, "estoy en evento onItemSelected $position $selectedText", Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Toast.makeText(this@FormActivity,"estoy en evento onNothingSelected",Toast.LENGTH_SHORT).show()
            }

        }

        binding.swtFinancialAssistance.setOnCheckedChangeListener{it,isChecked ->
        val ckecked = if (isChecked) "On" else "Off"
            Toast.makeText(this@FormActivity, "estoy en evento setOnCheckedChangeListener $ckecked",Toast.LENGTH_SHORT).show()
        }
*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itmList->{
                val intent = Intent(this@FormActivity,ListActivity::class.java)
                startActivity(intent)
            }
            R.id.itmEditar->{
                val intent = Intent(this@FormActivity,EditarEliminarActivity::class.java)
                startActivity(intent)
            }
            R.id.itmRecyclerView->{
                val intent = Intent(this@FormActivity,RecyclerActivity::class.java)
                startActivity(intent)
            }
            R.id.itmExit->{
                finish()

            }
        }


        return super.onOptionsItemSelected(item)
    }
   
}