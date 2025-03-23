package dev.giussepr.jetpackcompose.statemanagement.todolist

import androidx.lifecycle.ViewModel
import dev.giussepr.jetpackcompose.statemanagement.todolist.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoListViewModel : ViewModel() {

    private var _state = MutableStateFlow(TodoListState())
    var state = _state.asStateFlow()

    fun onIntent(intent: TodoListIntent) {
        when (intent) {
            TodoListIntent.LoadInitialTodos -> loadInitialTodos()
            is TodoListIntent.OnTodoItemChecked -> {
                val todos: List<Todo> = _state.value.todos.map { todo ->
                    if (todo.id == intent.todoId) {
                        todo.copy(isChecked = intent.isChecked)
                    } else {
                        todo
                    }
                }
                _state.update { it.copy(todos = todos) }
            }
        }
    }

    private fun loadInitialTodos() {
        val todos: List<Todo> = buildList {
            add(
                Todo(
                    id = 1,
                    title = "Lorem ipsum dolor sit amet consectetur, adipiscing elit curae a.",
                    description = "Ultrices euismod sed enim egestas venenatis, varius fames eros."
                )
            )
            add(
                Todo(
                    id = 2,
                    title = "Sollicitudin fusce egestas porta sed non, vivamus posuere leo vel.",
                    description = "Ac cubilia habitant nullam fermentum, convallis tempor sollicitudin, venenatis suscipit sem."
                )
            )
            add(
                Todo(
                    id = 3,
                    title = "Massa natoque eget fermentum lobortis, risus magnis per.",
                    description = "Laoreet tempus non porta, varius cursus tortor morbi, felis sociis."
                )
            )
            add(
                Todo(
                    id = 4,
                    title = "Id platea nullam senectus in, risus consequat vel.",
                    description = "Feugiat auctor quam ornare turpis, suspendisse dignissim gravida."
                )
            )
            add(
                Todo(
                    id = 5,
                    title = "Mauris viverra suspendisse morbi dictum lacus, donec blandit et.",
                    description = "Quam condimentum nisi nec vivamus vulputate ornare, venenatis potenti mus laoreet."
                )
            )
        }

        _state.update {
            it.copy(
                todos = todos
            )
        }
    }
}