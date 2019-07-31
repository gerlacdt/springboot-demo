package com.example.demo.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(UserController::class)
@ActiveProfiles("test")
class UserControllerMockUnitTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var userRepository: UserRepository

    val mapper = ObjectMapper().registerModule(KotlinModule())
            .registerModule(JavaTimeModule())

    @Test
    fun findAllTest() {
        val now = LocalDateTime.now()
        val fooUser = User(id= 1, firstname = "foo", surname = "foo", age = 10,
                email = "email@example.com", premium = true,
                createdAt = now, updatedAt = now)

        val barUser = User(id=2, firstname = "bar", surname = "bar", age = 12,
                email = "email@example.com", premium = false,
                createdAt = now, updatedAt = now)

        every { userRepository.findAll() } returns listOf(fooUser, barUser)
        val response = this.mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn()

        val users = mapper.readValue(response.response.contentAsString,
                UserGetAllResponse::class.java)

        println("users: ${users}")
        assertEquals(2, users.users.size)
    }
}