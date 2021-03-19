package com.example.estudianteseduardo.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.estudianteseduardo.Data.ListStudents
import com.example.estudianteseduardo.DetailActivity
import com.example.estudianteseduardo.EditarActivity
import com.example.estudianteseduardo.Entity.EntityStudent
import com.example.estudianteseduardo.R
import com.example.estudianteseduardo.Tools.Constants
import com.example.estudianteseduardo.databinding.ItemStudentBinding
import com.google.android.material.snackbar.Snackbar


class StudentAdapter(val studentList: Array<EntityStudent>,val context: Context) : RecyclerView.Adapter<StudentHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {

        val inflater = LayoutInflater.from(parent.context)
        return  StudentHolder(inflater.inflate(R.layout.item_student,parent,false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.txvFullName.text = "${studentList[position].name}  ${studentList[position].lastName}"
        holder.txvGender.text = if(studentList[position].gender==1) "masculino" else "Femenino"
        holder.txvDegree.text = studentList[position].degree

        holder.btnDelete.setOnClickListener{
            val listStudents= ListStudents()
            if(listStudents.delete(studentList[position].name)  )
            {
                notifyDataSetChanged()
                Toast.makeText(context, "Estudiante Eliminado", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(context, "No se elimino el estudiante", Toast.LENGTH_SHORT).show()
            }
        }
        holder.btnedit.setOnClickListener{
            val intent = Intent(context, EditarActivity::class.java).apply {
                putExtra(Constants.ID, position)
            }
            startActivity(context,intent,null)

        }
        holder.btnView.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(Constants.ID, position)
            }
            context.startActivity(intent)
        }
    }


}

class StudentHolder(view: View):RecyclerView.ViewHolder(view){

    val binding = ItemStudentBinding.bind(view)

    val imgLogo = binding.imvImagen
    val txvFullName =binding.txvFullName
    val txvGender = binding.txvGender
    val txvDegree = binding.txvDegree
    val btnDelete = binding.btnDelete
    val btnView = binding.btnView
    val btnedit = binding.btnedit
}
