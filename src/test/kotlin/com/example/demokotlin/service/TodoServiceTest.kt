package com.example.demokotlin.service

import com.example.demokotlin.domian.Todo
import com.example.demokotlin.domian.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

//@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class TodoServiceTest @Autowired constructor(
    val todoService: TodoService,
    val todoRepository: TodoRepository
) {

    /*@MockkBean
    lateinit var todoRepository: TodoRepository

    lateinit var todoService: TodoService*/

    val stub: Todo by lazy {
        Todo(
                id = 1,
                title = "test",
                description = "test",
                done = false,
                createdAt = LocalDateTime.now(),
                updatedAt = null
        )
    }


   /* @BeforeEach
    fun setup(){
        todoService  = TodoService(todoRepository)
    }*/



    @Test
    fun `모든 Todo 목록을 반환한다`() {
        // given
        var list = todoRepository.findAll()
        // when

        // then
        assertThat(list).isNotNull
        assertThat(list).size().isEqualTo(0)

    }

    @Test
    fun `존재 하지 않는 Id 대상 찾기`() {
        // given
        val id = 1L
        var todo: Todo?
        // when
        var exception = assertThrows<ResponseStatusException> {
            todo = todoService.findById(id)
        }
        // then
        assertThat(exception.status).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `Id를 이용하여 대상 Todo 항목을 찾아 반환한다`() {
        //given
      /*  every {
            todoRepository.findByIdOrNull(1)
        } returns stub*/


        // when
        val actual = todoService.findById(1)

        // then
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(stub)
    }

    @Test
    fun `새로운 Todo 생성한다`() {
        //

    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}