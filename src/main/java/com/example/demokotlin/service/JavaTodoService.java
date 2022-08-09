package com.example.demokotlin.service;

import com.example.demokotlin.api.model.JavaTodoRequest;
import com.example.demokotlin.domain.JavaTodo;
import com.example.demokotlin.domain.JavaTodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JavaTodoService {
    private final JavaTodoRepository todoRepository;

    public JavaTodoService(JavaTodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public List<JavaTodo> findAll() {
        return todoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional(readOnly = true)
    public JavaTodo findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public JavaTodo create(JavaTodoRequest request) {
        Assert.notNull(request, "TodoRequest is null");

        JavaTodo todo = JavaTodo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .done(false)
                .createdAt(LocalDateTime.now())
                .build();
        return todoRepository.save(todo);
    }

    @Transactional
    public JavaTodo update(Long id, JavaTodoRequest request) {
        Assert.notNull(request, "TodoRequest is null");

        JavaTodo todo = findById(id);
        todo.update(request.getTitle(),
                request.getDescription(),
                request.getDone());
        return todoRepository.save(todo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
