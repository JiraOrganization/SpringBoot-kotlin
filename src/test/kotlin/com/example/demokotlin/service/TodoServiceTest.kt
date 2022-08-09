package com.example.demokotlin.service

import com.example.demokotlin.api.model.TodoRequest
import com.example.demokotlin.domian.Todo
import com.example.demokotlin.domian.TodoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

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

    val stubs: List<Todo> by lazy {
        listOf(
            Todo(
                id = 1,
                title = "test1",
                description = "description1",
                done = false,
                createdAt = LocalDateTime.now(),
                updatedAt = null
            ),
            Todo(
                id = 2,
                title = "test2",
                description = "description2",
                done = false,
                createdAt = LocalDateTime.now(),
                updatedAt = null
            ),
            Todo(
                id = 3,
                title = "test3",
                description = "description3",
                done = false,
                createdAt = LocalDateTime.now(),
                updatedAt = null
            ),
            Todo(
                id = 4,
                title = "test4",
                description = "description4",
                done = false,
                createdAt = LocalDateTime.now(),
                updatedAt = null
            ), Todo(
                id = 5,
                title = "test5",
                description = "description5",
                done = false,
                createdAt = LocalDateTime.now(),
                updatedAt = null
            )
        )
    }

    val requests: List<TodoRequest> by lazy {
        listOf(
            TodoRequest(
                title = "test1",
                description = "description1"
            ),
            TodoRequest(
                title = "test2",
                description = "description2"
            ),
            TodoRequest(
                title = "test3",
                description = "description3"
            ),
            TodoRequest(
                title = "test4",
                description = "description4"
            ), TodoRequest(
                title = "test5",
                description = "description5"
            )
        )
    }


    /* @BeforeEach
     fun setup(){
         todoService  = TodoService(todoRepository)
     }*/


    @Test
    fun `모든 Todo 목록을 반환한다`() {
        // given
        val index = 0
        requests.forEach(todoService::create)

        // when
        var list = todoRepository.findAll()

        // then
        assertThat(list[index].id).isNotNull()
        assertThat(list[index].title).isEqualTo(requests[index].title)
        assertThat(list[index].description).isEqualTo(requests[index].description)
        assertThat(list[index].done).isEqualTo(requests[index].done)
        assertThat(list[index].createdAt).isNotNull()
        // TODO : updatedAt 기능 미구현으로 추후 적용
        //assertThat(list[index].updatedAt).isNotNull()
    }

@Test
fun `존재 하지 않는 Id 대상 찾기`() {
    // given
    val id = -9999L
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

