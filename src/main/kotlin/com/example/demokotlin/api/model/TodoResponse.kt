package com.example.demokotlin.api.model

import com.example.demokotlin.domian.Todo
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val description: String,
    val done : Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
) {

    companion object {
        fun of(todo: Todo?): TodoResponse {
            checkNotNull(todo) { "Todo can't be null" }
            checkNotNull(todo.id) { "Todo Id can't be null" }

            return TodoResponse(
                id = todo.id,
                //id = todo.id!!,
                title = todo.title,
                description = todo.description,
                done = todo.done,
                createdAt = todo.createdAt,
                updatedAt = todo.updatedAt
            )
        }

    }
}