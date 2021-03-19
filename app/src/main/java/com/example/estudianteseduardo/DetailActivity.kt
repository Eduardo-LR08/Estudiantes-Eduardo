package com.example.estudianteseduardo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.widget.Toast
import com.example.estudianteseduardo.Data.ListStudents
import com.example.estudianteseduardo.Tools.Constants
import com.example.estudianteseduardo.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private val listStudents= ListStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.text_detail)

        val id: Int = intent.getIntExtra(Constants.ID, -1)

        if (id!=-1)
        {
            val student = listStudents.getStudent(id)

            binding.txvFullName.text = "${student.name} ${student.lastName}"
            binding.txvGender.text = "${if (student.gender==1) "Masulino" else if (student.gender==2) "Femenino" else "genero no seleccionado"} "
            binding.txvDegree.text = "${student.degree}"
            binding.txvFinancialAssistance.text = "${if(student.financialAssistance) "Con beca" else "Sin beca"}"
            binding.txvHobbies.text = "Pasa Tiempos: ${if(student.read)"leer" else ""} ${if(student.travel)"viajar" else ""} ${if(student.sport)"deportes" else ""}"

            binding.btnDelete.setOnClickListener{
                if(listStudents.delete(student.name))
                {
                    Toast.makeText(this@DetailActivity,"Elemento eliminado",Toast.LENGTH_SHORT).show()
                    //finish()
                    super.onBackPressed()
                }
                else {
                    Toast.makeText(this@DetailActivity,"Elemento No eliminado",Toast.LENGTH_SHORT).show()
                }
            }
        }
        else
        {
            Toast.makeText(this@DetailActivity,"Se genero un problema al intentar cargar esta pagina",Toast.LENGTH_SHORT).show()
            //finish()
            super.onBackPressed()
        }

    }
}