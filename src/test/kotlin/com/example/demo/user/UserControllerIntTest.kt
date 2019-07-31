package com.example.demo.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntTest(@Autowired val restTemplate: TestRestTemplate) {

    @LocalServerPort
    var port: Int? = null

    @Test
    fun insertUserTest() {
        val request = HttpEntity(UserInsertRequest("firstname",
                "surname", 39, "email", false))
        val response = restTemplate.postForObject("http://localhost:${port}/api/users",
                request, UserInsertResponse::class.java)
        println("response: ${response}")
        assertTrue(response.id > 0)
    }

    @Test
    fun insertInvalidUserTest() {
        val userWithBlankEmail = UserInsertRequest("firstname3", "surname", 39,
                "", false)
        val request = HttpEntity(userWithBlankEmail)
        val response = restTemplate.postForEntity("http://localhost:${port}/api/users",
                request, UserInsertResponse::class.java)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }
}