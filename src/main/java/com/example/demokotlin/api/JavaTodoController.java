package com.example.demokotlin.api;

import com.example.demokotlin.api.model.JavaTodoListResponse;
import com.example.demokotlin.api.model.JavaTodoRequest;
import com.example.demokotlin.api.model.JavaTodoResponse;
import com.example.demokotlin.domain.JavaTodo;
import com.example.demokotlin.service.JavaTodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/java/todos")
public class JavaTodoController {
    private final JavaTodoService todoService;

    public JavaTodoController(JavaTodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<JavaTodoListResponse> getAll() {
        List<JavaTodo> todos = todoService.findAll();
        return ResponseEntity.ok(JavaTodoListResponse.of(todos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JavaTodoResponse> get(@PathVariable Long id) {
        JavaTodo todo = todoService.findById(id);
        return ResponseEntity.ok(JavaTodoResponse.of(todo));
    }

    @PostMapping
    public ResponseEntity<JavaTodoResponse> create(@RequestBody JavaTodoRequest request) {
        JavaTodo todo = todoService.create(request);
        return ResponseEntity.ok(JavaTodoResponse.of(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JavaTodoResponse> update(@PathVariable Long id,
                                                   @RequestBody JavaTodoRequest request) {
        JavaTodo todo = todoService.update(id, request);
        return ResponseEntity.ok(JavaTodoResponse.of(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
