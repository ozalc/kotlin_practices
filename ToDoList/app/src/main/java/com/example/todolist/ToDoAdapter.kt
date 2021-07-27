package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter(

    val todos: MutableList<ToDo>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        )
    }

    fun addTodo(todo: ToDo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)

    }

    fun deleteDoneTodos()
    {
        todos.removeAll { todo ->
            todo.ischecked
        }
            notifyDataSetChanged()

    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags=tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else
        {
            tvTodoTitle.paintFlags=tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
        tvToDoTitle.text=currentTodo.title
        cbToDo.isChecked = currentTodo.ischecked
        toggleStrikeThrough(tvToDoTitle, currentTodo.ischecked)
        cbToDo.setOnCheckedChangeListener { _, isChecked -> toggleStrikeThrough(tvToDoTitle,isChecked)
        currentTodo.ischecked=!currentTodo.ischecked
        }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}