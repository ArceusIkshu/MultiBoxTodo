package com.example.multicheckboxtodo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.multicheckboxtodo.data.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Upsert
    suspend fun upsertTodo(todoEntity: TodoEntity)

    @Delete
    suspend fun deleteTodo(todoEntity: TodoEntity)

    @Query("SELECT * from todos ORDER BY id")
    fun getAllTodosById() : Flow<List<TodoEntity>>

}