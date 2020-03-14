package com.example.demo.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryIntTest(@Autowired val userRepository: UserRepository) {

    @BeforeEach
    fun beforeEach() {
        userRepository.truncate()
    }

    @Test
    fun insertTest() {
        val u = User(
            null, "firstname2", "surname", 39,
            "email@example.com", false
        )
        val id = userRepository.insert(u)
        assert(id > 0)
    }

    @Test
    fun findByIdTest() {
        val u = User(
            null, "firstname", "surname", 39,
            "email@example.com", false
        )
        val id = userRepository.insert(u)
        val user = userRepository.findById(id)
        assertNotNull(user)
        assertEquals(id, user?.id)
    }

    @Test
    fun findAllTest() {
        val u = User(
            null, "firstname", "surname", 39,
            "email@example.com", false
        )
        val id = userRepository.insert(u)
        val u2 = User(
            null, "firstname", "surname", 39,
            "email@example.com", false
        )
        val id2 = userRepository.insert(u)

        val users = userRepository.findAll()
        assert(users.size >= 2)
    }

    @Test
    fun deleteTest() {
        val u = User(
            null, "daniel", "surname", 39,
            "email@example.com", false
        )
        val id = userRepository.insert(u)
        val rowsProcessed = userRepository.delete(id)
        assertEquals(1, rowsProcessed)

        assertThrows(NotFoundException::class.java, {
            val user = userRepository.findById(id)
        })
    }
}
