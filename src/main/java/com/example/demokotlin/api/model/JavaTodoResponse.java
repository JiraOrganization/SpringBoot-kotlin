package com.example.demokotlin.api.model;

import com.example.demokotlin.domain.JavaTodo;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Data
@Builder
public class JavaTodoResponse {
    private Long id;

    private String title;

    private String description;

    private Boolean done;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static JavaTodoResponse of(JavaTodo todo) {
        Assert.notNull(todo, "Todo is null");

        return JavaTodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .done(todo.getDone())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
}
