package com.example.demo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun beforeEach() {
        userRepository.truncate()
    }

    @Test
    fun insertTest() {
        val u = User(null, "firstname2", "surname", 39,
                "email@example.com", false)
        val id = userRepository.insert(u)
        assert(id > 0)
    }

    @Test
    fun findByIdTest() {
        val u = User(null, "firstname", "surname", 39,
                "email@example.com", false)
        val id = userRepository.insert(u)
        val user = userRepository.findById(id)
        assertNotNull(user)
        u.id = id
        assertEquals(u, user)
    }

    @Test
    fun findAllTest() {
        val u = User(null, "firstname", "surname", 39,
                "email@example.com", false)
        val id = userRepository.insert(u)
        val u2 = User(null, "firstname", "surname", 39,
                "email@example.com", false)
        val id2 = userRepository.insert(u)

        val users = userRepository.findAll()
        assert(users.size >= 2)
    }


    @Test
    fun deleteTest() {
        val u = User(null, "daniel", "surname", 39,
                "email@example.com", false)
        val id = userRepository.insert(u)
        val rowsProcessed = userRepository.delete(id)
        assertEquals(1, rowsProcessed)

        val user = userRepository.findById(id)
         assertNull(user)
    }
}