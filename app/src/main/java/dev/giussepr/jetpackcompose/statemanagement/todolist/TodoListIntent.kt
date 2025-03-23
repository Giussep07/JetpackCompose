package dev.giussepr.jetpackcompose.statemanagement.todolist

sealed interface TodoListIntent {
    data object LoadInitialTodos: TodoListIntent
    data class OnTodoItemChecked(val isChecked: Boolean, val todoId: Int): TodoListIntent
}