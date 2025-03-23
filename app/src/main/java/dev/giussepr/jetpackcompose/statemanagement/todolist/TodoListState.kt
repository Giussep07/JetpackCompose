package dev.giussepr.jetpackcompose.statemanagement.todolist

import dev.giussepr.jetpackcompose.statemanagement.todolist.model.Todo

data class TodoListState(
    val todos: List<Todo> = emptyList()
)
