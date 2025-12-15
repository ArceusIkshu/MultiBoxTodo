package com.example.multicheckboxtodo.presentation.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.multicheckboxtodo.data.database.TodoDatabase
import com.example.multicheckboxtodo.presentation.theme.MultiCheckBoxTodoTheme
import com.example.multicheckboxtodo.presentation.viewmodel.TodoViewModel


class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            name = "todo_database"
        ).build()
    }

    private val viewModel by viewModels<TodoViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiCheckBoxTodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AddTodoBottomBar(viewModel = viewModel)
                    }) { innerPadding ->
                    TodoScreen(modifier = Modifier.padding(innerPadding), viewModel = viewModel)
                }
            }
        }
    }
}