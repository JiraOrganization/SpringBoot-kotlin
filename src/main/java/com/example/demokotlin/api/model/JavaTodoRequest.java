package com.example.demokotlin.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JavaTodoRequest {
    private String title;

    private String description;

    private Boolean done = false;
}
