package com.example.multicheckboxtodo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multicheckboxtodo.data.dao.TodoDao
import com.example.multicheckboxtodo.data.entity.TodoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val todoDao: TodoDao): ViewModel() {
    private val _todos = MutableStateFlow<List<TodoEntity>>(emptyList())
    val todos: StateFlow<List<TodoEntity>> = _todos

    init{
        viewModelScope.launch {
            todoDao.getAllTodosById().collect {todos ->
                _todos.value = todos
            }
        }
    }

    fun insertTodo(todoTitle: String){
        val todo = TodoEntity(title = todoTitle, statuses = List(3){false})
        viewModelScope.launch {
            todoDao.upsertTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoEntity){
        viewModelScope.launch {
            todoDao.deleteTodo(todo)
        }
    }

    fun updateTodo(todoEntity: TodoEntity, newStatuses: List<Boolean>){
        viewModelScope.launch {
            todoDao.upsertTodo(todoEntity = todoEntity.copy(statuses = newStatuses))
        }
    }
}