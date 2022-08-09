package com.example.demokotlin.api

import com.example.demokotlin.api.model.TodoListResponse
import com.example.demokotlin.api.model.TodoRequest
import com.example.demokotlin.api.model.TodoResponse
import com.example.demokotlin.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(
    private val todoService: TodoService
){

    @GetMapping
    fun getAll() = ok(TodoListResponse.of(todoService.findAll()))


    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = ok(TodoResponse.of(todoService.findById(id)))

    @PostMapping
    fun create(@RequestBody request: TodoRequest) = ok(TodoResponse.of(todoService.create(request)))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: TodoRequest) = ok(TodoResponse.of(todoService.update(id, request)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<UInt> {
        todoService.delete(id)
        return noContent().build()
    }
}