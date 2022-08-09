package com.example.demokotlin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JavaTodoRepository extends JpaRepository<JavaTodo, Long> {
    Optional<List<JavaTodo>> findAllByDoneIsFalseOrderByIdDesc();
}
