package dev.giussepr.jetpackcompose.statemanagement.todolist

import androidx.lifecycle.ViewModel
import dev.giussepr.jetpackcompose.statemanagement.todolist.model.TodoItemType.Todo
import dev.giussepr.jetpackcompose.statemanagement.todolist.model.TodoItemType
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
                val todos: List<TodoItemType> = _state.value.todos.map { todo ->
                    if (todo is Todo && todo.id == intent.todoId) {
                        todo.copy(isChecked = intent.isChecked)
                    } else {
                        todo
                    }
                }
                _state.update { it.copy(todos = todos) }
            }

            is TodoListIntent.OnAddTodoTitleValueChange -> {
                val todos: List<TodoItemType> = _state.value.todos.map { todo ->
                    if (todo is TodoItemType.AddTodo) {
                        todo.copy(title = intent.value)
                    } else {
                        todo
                    }
                }
                _state.update { it.copy(todos = todos) }
            }

            is TodoListIntent.OnAddTodoDescriptionValueChange -> {
                val todos: List<TodoItemType> = _state.value.todos.map { todo ->
                    if (todo is TodoItemType.AddTodo) {
                        todo.copy(description = intent.value)
                    } else {
                        todo
                    }
                }
                _state.update { it.copy(todos = todos) }
            }

            is TodoListIntent.OnAddNewTodo -> {
                val lastTodo = _state.value.todos.filterIsInstance<Todo>().lastOrNull()
                val todo = Todo(
                    id = (lastTodo?.id?.plus(1)) ?: 1,
                    title = intent.title,
                    description = intent.description
                )
                val todos = _state.value.todos.toMutableList()

                val indexToInsert = todos.indexOfFirst { it is TodoItemType.AddTodo }.takeIf { it >= 0 } ?: todos.size

                todos.add(indexToInsert, todo)

                val updatedTodos = todos.map {
                    if (it is TodoItemType.AddTodo) {
                        TodoItemType.AddTodo()
                    } else {
                        it
                    }
                }

                _state.update {
                    it.copy(todos = updatedTodos)
                }
            }

            is TodoListIntent.OnDeleteClicked -> {
                val todos = _state.value.todos.toMutableList()
                if (intent.todoIndex in todos.indices) {
                    todos.removeAt(intent.todoIndex)
                    _state.update {
                        it.copy(todos = todos)
                    }
                }
            }
        }
    }

    private fun loadInitialTodos() {
        val todos: List<TodoItemType> = buildList {
            add(
                TodoItemType.AddTodo()
            )
        }

        _state.update {
            it.copy(
                todos = todos
            )
        }
    }
}