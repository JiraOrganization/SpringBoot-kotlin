package com.example.demokotlin.api.model;

import com.example.demokotlin.domain.JavaTodo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class JavaTodoListResponse {
    private final List<JavaTodoResponse> items;

    private JavaTodoListResponse(List<JavaTodoResponse> items) {
        this.items = items;
    }

    public int size() {
        return items.size();
    }

    public JavaTodoResponse get(int index) {
        return items.get(index);
    }

    public static JavaTodoListResponse of(List<JavaTodo> todoList) {
        List<JavaTodoResponse> todoListResponse = todoList.stream()
                .map(JavaTodoResponse::of)
                .collect(Collectors.toList());

        return new JavaTodoListResponse(todoListResponse);
    }
}
