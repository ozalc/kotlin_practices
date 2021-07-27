package com.example.todolist

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*
var a = 0
var list = arrayListOf<Int>()
class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = ToDoAdapter(mutableListOf())

        rvToDoItems.adapter=todoAdapter
        rvToDoItems.layoutManager = LinearLayoutManager(this)

        btnAddToDo.setOnClickListener {

            val todoTitle = etToDoTitle.text.toString()
            if (todoTitle.isNotEmpty() )
            {
                val todo = ToDo(todoTitle)
                todoAdapter.addTodo(todo)
                etToDoTitle.text.clear()

            }
        }



        btnDeleteDoneToDos.setOnClickListener {
            todoAdapter.deleteDoneTodos()

            /*val sharedPreferences = this.getSharedPreferences(packageName,android.content.Context.MODE_PRIVATE)


            sharedPreferences.edit().remove("todoTitle" + a).apply()
            sharedPreferences.edit().remove("isChecked" + a).apply()*/
        }


        loadData()
    }

    override fun onStop() {
        a=0
        val onStopList = todoAdapter.todos

        val sharedPreferences: SharedPreferences = this.getSharedPreferences( "SharedPrefs",Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()

        editor.clear().apply()

        for (todo in onStopList)
        {
            editor.apply {
                putString("todoTitle" + a, todo.title)
                putBoolean("isChecked" + a,todo.ischecked)
                apply()
            }
            a++
            list.add(a)
        }


        super.onStop()
    }

    fun loadData(){
        val sharedPreferences: SharedPreferences = this.getSharedPreferences( "SharedPrefs",Context.MODE_PRIVATE)
            var temp = 0
            /*for (i in /*0..list.size*/ list)*/while(temp <= 0){
                val savedString = sharedPreferences.getString("todoTitle" + a, null)
                val savedBoolean = sharedPreferences.getBoolean("isChecked" + a, false)

                if(savedString!=null)
                {
                    val todo = ToDo(savedString.toString(), savedBoolean)

                    todoAdapter.addTodo(todo)
                    a++
                }
            else
                {
                    temp = 1
                }


            }


    }


}