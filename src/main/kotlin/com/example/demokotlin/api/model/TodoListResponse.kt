package com.example.demokotlin.api.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.example.demokotlin.domian.Todo

class TodoListResponse(
    val items: List<TodoResponse>,
){
    val size : Int
        @JsonIgnore // ignore this field when serializing to JSON
        get() = items.size

    fun get(index: Int) = items[index]

    companion object {
        fun of(todoList: List<Todo>) =
            TodoListResponse(todoList.map(TodoResponse::of))
    }


}