package com.example.estudianteseduardo.Data

import android.util.Log
import com.example.estudianteseduardo.Entity.EntityStudent
import com.example.estudianteseduardo.Tools.Constants

class ListStudents {



    fun add(student: EntityStudent):Int{
        var answer= -1
        if (!existNameFilter(student.name)){
            listStudents.add(student)
            answer = listStudents.size -1

        }
        return answer;
    }

    fun delete(name:String):Boolean{
        var answer:Boolean = false

        for ((index,item) in listStudents.withIndex()){

            if (item.name == name){
                listStudents.removeAt(index)
                answer=true;
                break;
            }
        }
            return answer;
    }
    fun edit(name:String,student:EntityStudent):Boolean{
        var answer:Boolean = false

        for ((index,item) in listStudents.withIndex()){

            if (item.name == name){

                listStudents[index].gender = student.gender
                listStudents[index].degree = student.degree
                answer= true;
                break;

            }
        }
        return answer;
    }

    fun showListStudents(){
        Log.d(Constants.LOG_TAG,"${listStudents.size}")
        for((index,item) in listStudents.withIndex()){
            Log.d(Constants.LOG_TAG,"$index ${item.name} ${item.degree} ${item.gender}")
            //Log.d(Constants.LOG_TAG,"$index ${listStudents[index].name} ${listStudents[index].degree} ${listStudents[index].gender}")
        }
    }

    fun existsName(name:String):Boolean {
        var answer: Boolean = false
        for (element in listStudents) {
            if (element.name == name) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    fun existNameFilter(name:String):Boolean{
        var answer: Boolean = false
        if (listStudents.filter { e -> e.name == name }.count()==1)
        {
            answer=true
        }

        return answer
    }
    fun getStringArray():Array<String>{
        val answareList= arrayListOf<String>()
        for((index,item)in listStudents.withIndex()){
            answareList.add("${item.name} ${item.lastName} " +
                    "${  if (item.gender==1) "Masulino" else if (item.gender==2) "Femenino" else "genero no seleccionado"}  ${item.degree} ")
        }
        return answareList.toTypedArray()
    }

    fun getEntityStudentArray(): Array<EntityStudent> {
        return listStudents.toTypedArray()
    }
    fun getStudent(index:Int):EntityStudent{
        return listStudents[index]
    }

    companion object{
        private var listStudents = arrayListOf<EntityStudent>()
    }
}