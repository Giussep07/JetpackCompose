package dev.giussepr.jetpackcompose.statemanagement.todolist.model

sealed interface TodoItemType {
    data class Todo(
        val id: Int,
        val title: String,
        val description: String,
        val isChecked: Boolean = false
    ): TodoItemType

    data class AddTodo(val title: String = "", val description: String = ""): TodoItemType
}