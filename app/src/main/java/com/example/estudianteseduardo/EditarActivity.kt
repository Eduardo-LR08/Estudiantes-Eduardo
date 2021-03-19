package com.example.estudianteseduardo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Toast
import com.example.estudianteseduardo.Data.ListStudents
import com.example.estudianteseduardo.Entity.EntityStudent
import com.example.estudianteseduardo.Tools.Constants
import com.example.estudianteseduardo.databinding.ActivityDetailBinding
import com.example.estudianteseduardo.databinding.ActivityEditarBinding
import com.google.android.material.snackbar.Snackbar

class EditarActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditarBinding
    private val listStudents= ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_EditarEstudiante)

        val id: Int = intent.getIntExtra(Constants.ID, -1)

        if (id!=-1)
        {
            val student = listStudents.getStudent(id)

            binding.edtName.setText(student.name)
            binding.edtLastName.setText(" ${student.lastName}")
            binding.spnGender.setSelection(student.gender)
            //binding.rgdDegree.check(binding.rgdDegree.id) = (student.degree)
            binding.swtFinancialAssistance.isChecked = (student.financialAssistance)
            binding.ckbRead.isChecked = (student.read)
            binding.ckbSport.isChecked = (student.travel)
            binding.ckbTravel.isChecked = (student.sport)
        }
        else
        {
            Toast.makeText(this@EditarActivity,"Se genero un problema al intentar cargar esta pagina",
                Toast.LENGTH_SHORT).show()
            //finish()
            super.onBackPressed()
        }

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

                    Toast.makeText(this@EditarActivity, "Estudiante Editado.", Toast.LENGTH_SHORT).show()
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
                    Snackbar.make(it, "Estudiante No Editado.", Snackbar.LENGTH_SHORT).show()
                }
            }



        }


    }

}