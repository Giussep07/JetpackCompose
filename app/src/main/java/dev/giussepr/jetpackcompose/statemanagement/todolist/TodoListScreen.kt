package dev.giussepr.jetpackcompose.statemanagement.todolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.giussepr.jetpackcompose.statemanagement.todolist.model.Todo
import dev.giussepr.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun TodoListScreen(modifier: Modifier = Modifier) {
    val viewModel = viewModel<TodoListViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(TodoListIntent.LoadInitialTodos)
    }

    TodoListContent(
        modifier = modifier,
        todos = state.todos,
        onCheckedChange = { isChecked, todoId ->
            viewModel.onIntent(TodoListIntent.OnTodoItemChecked(isChecked, todoId))
        }
    )
}

@Composable
fun TodoListContent(
    modifier: Modifier = Modifier,
    todos: List<Todo>,
    onCheckedChange: (Boolean, Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(todos) { todo ->
            TodoListItem(
                todo = todo,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
fun TodoListItem(
    modifier: Modifier = Modifier,
    todo: Todo,
    onCheckedChange: (Boolean, Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                val textDecoration =
                    if (todo.isChecked) TextDecoration.LineThrough else TextDecoration.None
                Text(
                    text = todo.title,
                    textDecoration = textDecoration,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = todo.description,
                    textDecoration = textDecoration,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Checkbox(checked = todo.isChecked, onCheckedChange = {
                onCheckedChange(it, todo.id)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TodoListScreenPreview() {
    val todos = List(10) { index ->
        Todo(
            id = index,
            title = "Title $index",
            description = "Description $index",
            isChecked = index % 2 == 0
        )
    }
    JetpackComposeTheme {
        TodoListContent(todos = todos, onCheckedChange = { _, _ -> })
    }
}