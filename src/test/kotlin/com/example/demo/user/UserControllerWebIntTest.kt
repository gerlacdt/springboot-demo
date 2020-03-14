package com.example.demo.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserControllerWebIntTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var userRepository: UserRepository

    @LocalServerPort
    var port: Int? = null


    @BeforeEach
    fun beforeEach() {
        userRepository.truncate()
    }


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
                request, String::class.java)

        println("response: ${response}")
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun updateUserTest() {
        val user = User(42, "firstname", "surname", 20, "email21", true)
        val request = HttpEntity(user)
        val response = restTemplate.exchange("http://localhost:${port}/api/users/42", HttpMethod.PUT,
                request, String::class.java)

        println("PUT response: ${response}")
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.statusCode)
    }
    @Test
    fun findByIdNotFoundTest() {
        val nonExistingId = 42
        val response = restTemplate.
                getForEntity("http://localhost:${port}/api/users/${nonExistingId}", ErrorResponse::class.java)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertEquals(ErrorResponse(HttpStatus.NOT_FOUND.value(), "User with ID 42 not found.", listOf<String>()), response.body)
    }
}