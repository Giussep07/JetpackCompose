package dev.giussepr.jetpackcompose.statemanagement.todolist

import dev.giussepr.jetpackcompose.statemanagement.todolist.model.TodoItemType

data class TodoListState(
    val todos: List<TodoItemType> = emptyList()
)
