@file:OptIn(ExperimentalMaterial3Api::class)

package dev.giussepr.jetpackcompose.statemanagement.todolist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.giussepr.jetpackcompose.components.AppTextField
import dev.giussepr.jetpackcompose.statemanagement.todolist.model.TodoItemType
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
        todosItems = state.todos,
        onIntent = viewModel::onIntent
    )
}

@Composable
fun TodoListContent(
    modifier: Modifier = Modifier,
    todosItems: List<TodoItemType>,
    onIntent: (TodoListIntent) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(todosItems) { index, todoItem ->
            when (todoItem) {
                is TodoItemType.Todo -> TodoListItem(
                    index = index,
                    todo = todoItem,
                    onCheckedChange = { isChecked, todoId ->
                        onIntent(TodoListIntent.OnTodoItemChecked(isChecked, todoId))
                    },
                    onDeleteClicked = { todoIndex ->
                        onIntent(TodoListIntent.OnDeleteClicked(todoIndex))
                    }
                )

                is TodoItemType.AddTodo -> {
                    Card {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            var showDescription by remember { mutableStateOf(false) }
                            AppTextField(
                                value = todoItem.title,
                                placeholder = "Add TODO",
                                onValueChange = {
                                    onIntent(TodoListIntent.OnAddTodoTitleValueChange(it))
                                    showDescription = it.isNotEmpty()
                                },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                            )
                            AnimatedVisibility(showDescription) {
                                AppTextField(
                                    value = todoItem.description,
                                    placeholder = "TODO description",
                                    onValueChange = {
                                        onIntent(TodoListIntent.OnAddTodoDescriptionValueChange(it))
                                    },
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            onIntent(
                                                TodoListIntent.OnAddNewTodo(
                                                    todoItem.title,
                                                    todoItem.description
                                                )
                                            )
                                        }
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TodoListItem(
    modifier: Modifier = Modifier,
    index: Int,
    todo: TodoItemType.Todo,
    onCheckedChange: (Boolean, Int) -> Unit,
    onDeleteClicked: (Int) -> Unit
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
            Checkbox(
                checked = todo.isChecked, onCheckedChange = {
                    onCheckedChange(it, todo.id)
                })
            IconButton(onClick = {
                onDeleteClicked(index)
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Todo item"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TodoListScreenPreview() {
    val todos = buildList {
        add(
            TodoItemType.Todo(
                id = 1,
                title = "Title",
                description = "Description",
                isChecked = false
            )
        )
        add(TodoItemType.AddTodo())
    }
    JetpackComposeTheme {
        TodoListContent(
            todosItems = todos,
            onIntent = {})
    }
}