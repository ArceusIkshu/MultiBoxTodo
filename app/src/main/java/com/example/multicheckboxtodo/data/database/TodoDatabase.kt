package com.example.multicheckboxtodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.multicheckboxtodo.data.converters.TodoConverters
import com.example.multicheckboxtodo.data.dao.TodoDao
import com.example.multicheckboxtodo.data.entity.TodoEntity


@Database(
    entities = [TodoEntity::class],
    version = 1
)

@TypeConverters(TodoConverters::class)
abstract class TodoDatabase: RoomDatabase() {
    abstract val dao: TodoDao
}