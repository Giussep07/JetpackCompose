package dev.giussepr.jetpackcompose.statemanagement.todolist.model

data class Todo(
    val id: Int,
    val title: String,
    val description: String,
    val isChecked: Boolean = false
)
