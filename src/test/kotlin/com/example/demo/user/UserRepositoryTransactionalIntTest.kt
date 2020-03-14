package com.example.demo.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@JdbcTest
@Import(UserRepository::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @Transactional(propagation = Propagation.NOT_SUPPORTED)
@Transactional
@ActiveProfiles("test")
class UserRepositoryTransactionalIntTest(@Autowired val userRepository: UserRepository) {

    // only needed once because tests run in a transaction
    @BeforeAll
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    fun beforeAll() {
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
        assert(users.size == 2, { "actual users.size: ${users.size}" })
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
