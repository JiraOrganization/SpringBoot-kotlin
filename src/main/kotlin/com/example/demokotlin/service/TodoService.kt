package com.example.demokotlin.service

import com.example.demokotlin.api.model.TodoRequest
import com.example.demokotlin.domian.Todo
import com.example.demokotlin.domian.TodoRepository
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.domain.Sort.by
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime


@Service
class TodoService(
    private val todoRepository: TodoRepository
) {
    @Transactional(readOnly = true)
    fun findAll() : List<Todo> = todoRepository.findAll(by(Direction.DESC, "id"))

    fun findById(id: Long) = todoRepository.findByIdOrNull(id)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found Todo")

    @Transactional
    fun create(request: TodoRequest?) : Todo {
        checkNotNull(request){"request is null"}

        val todo = Todo(
            title = request.title,
            description = request.description,
            done = request.done,
            createdAt = LocalDateTime.now(),
        )

        return todoRepository.save(todo)
    }

    @Transactional
    fun update(id: Long, request: TodoRequest?) : Todo {
        checkNotNull(request){"request is null"}
        return findById(id).let {
            it.update(  request.title,
                request.description,
                request.done)

            todoRepository.save(it)
        }
    }

    fun delete(id: Long) = todoRepository.deleteById(id)
}