package com.example.multicheckboxtodo.presentation.ui



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.multicheckboxtodo.presentation.viewmodel.TodoViewModel

@Composable
fun AddTodoBottomBar(viewModel: TodoViewModel) {
    BottomAppBar() {
        var todoTitle by remember {
            mutableStateOf("")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = todoTitle,
                onValueChange = {
                    todoTitle = it
                },
                label = { Text(text = "Todo Title") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                maxLines = 1
            )
            Button(
                onClick = {
                    if (todoTitle.isNotBlank()) {
                        viewModel.insertTodo(todoTitle)
                        todoTitle = ""
                    }
                },
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(text = "Add")
            }

        }
    }
}