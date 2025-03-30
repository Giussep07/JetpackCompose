package dev.giussepr.jetpackcompose.statemanagement.todolist

sealed interface TodoListIntent {
    data object LoadInitialTodos: TodoListIntent
    data class OnTodoItemChecked(val isChecked: Boolean, val todoId: Int): TodoListIntent
    data class OnAddTodoTitleValueChange(val value: String): TodoListIntent
    data class OnAddTodoDescriptionValueChange(val value: String): TodoListIntent
    data class OnAddNewTodo(val title: String, val description: String): TodoListIntent
    data class OnDeleteClicked(val todoIndex: Int): TodoListIntent
}