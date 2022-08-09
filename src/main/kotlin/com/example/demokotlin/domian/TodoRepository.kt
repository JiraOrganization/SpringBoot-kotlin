package com.example.demokotlin.domian

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {

    fun findAllByDoneIsFalseOrderByIdDesc(): List<Todo>?
}