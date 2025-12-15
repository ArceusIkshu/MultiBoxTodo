package com.example.multicheckboxtodo.presentation.ui


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.multicheckboxtodo.data.entity.TodoEntity
import com.example.multicheckboxtodo.presentation.viewmodel.TodoViewModel


@Composable
fun TodoScreen(modifier: Modifier, viewModel: TodoViewModel) {
    val todos by viewModel.todos.collectAsState(initial = emptyList())
    TodoList(todos = todos, viewModel = viewModel, modifier = modifier)

}

@Composable
fun TodoList(todos: List<TodoEntity>, viewModel: TodoViewModel, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        items(todos) { todo ->
            TodoListItem(
                todo = todo,
                onDelete = {
                    viewModel.deleteTodo(todo)
                },
                onStatusChange = { newStatus ->
                    viewModel.updateTodo(todo, newStatuses = newStatus)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TodoListItem(todo: TodoEntity, onDelete: () -> Unit, onStatusChange: (List<Boolean>) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = todo.title, modifier = Modifier.weight(.9f), fontWeight = FontWeight.Bold)
            IconButton(
                onClick = {
                    onDelete()
                }, modifier = Modifier.weight(.1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = Color.Red
                )
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

            val titles = listOf("Pending", "In Progress", "Completed")
            todo.statuses.forEachIndexed { index, status ->
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = titles[index])
                    Checkbox(checked = status, onCheckedChange ={ newStatus ->
                        val newStatuses = todo.statuses.toMutableList()
                        newStatuses[index] = newStatus
                        onStatusChange(newStatuses)
                    } )
                }
            }

        }
    }
}