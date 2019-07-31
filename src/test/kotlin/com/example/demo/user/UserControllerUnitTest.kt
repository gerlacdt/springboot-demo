package com.example.demo.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerUnitTest(@Autowired val userRepository: UserRepository,
                             @Autowired val mapper: ObjectMapper) {

    @BeforeEach
    fun beforeEach() {
        userRepository.truncate()
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun insertUserTest() {
        // insert user via http call
        val u = User(null, "firstname3", "surname", 39,
                "email@example.com", false)
        val response = this.mockMvc.perform(
                post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(u)))
                .andExpect(status().isCreated)
                .andReturn()

        val result = mapper.readValue(response.response.contentAsString,
                UserInsertResponse::class.java)

        assertNotNull(result)
        assert(result.id > 0)
    }

    @Test
    fun findByIdTest() {
        val u = User(null, "firstname3", "surname", 39,
                "email@example.com", false)
        val response = this.mockMvc.perform(
                post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(u)))
                .andExpect(status().isCreated)
                .andReturn()
        val result = mapper.readValue(response.response.contentAsString,
                UserInsertResponse::class.java)

        assertNotNull(result)
        assert(result.id > 0)

        val getResponse = this.mockMvc.perform(get("/api/users/${result.id}")).andExpect(status().isOk).andReturn()
        val returnedUser = mapper.readValue(getResponse.response.contentAsString, User::class.java)
        val id = result.id

        assertEquals(id, returnedUser.id)
        assertEquals(u.firstname, returnedUser.firstname)
        assertEquals(u.surname, returnedUser.surname)
        assertEquals(u.age, returnedUser.age)
        assertEquals(u.email, returnedUser.email)
        assertEquals(u.premium, returnedUser.premium)
        assertNotNull(returnedUser.createdAt)
        assertNotNull(returnedUser.updatedAt)
    }

    @Test
    fun findAllTest() {
        val u = User(null, "firstname3", "surname", 39,
                "email@example.com", false)
        val u2 = User(null, "firstname4", "surname", 39,
                "email@example.com", false)
        val mvcResponse = this.mockMvc.perform(
                post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(u)))
                .andExpect(status().isCreated)
                .andReturn()
        val mvcResponse2 = this.mockMvc.perform(
                post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(u2)))
                .andExpect(status().isCreated)
                .andReturn()

        val mvcResponse3 = this.mockMvc.perform(get("/api/users")).andExpect(status().isOk).andReturn()
        val users = mapper.readValue(mvcResponse3.response.contentAsString, UserGetAllResponse::class.java)

        assertEquals(2, users.users.size)
    }

    @Test
    fun deleteTest() {
        val u = User(null, "firstname3", "surname", 39,
                "email@example.com", false)
        val u2 = User(null, "firstname4", "surname", 39,
                "email@example.com", false)
        val mvcResponse = this.mockMvc.perform(
                post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(u)))
                .andExpect(status().isCreated)
                .andReturn()
        val mvcResponse2 = this.mockMvc.perform(
                post("/api/users").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(u2)))
                .andExpect(status().isCreated)
                .andReturn()

        // delete one user
        val ( id ) = mapper.readValue(mvcResponse2.response.contentAsString, UserInsertResponse::class.java)
        val mvcDeleteResponse = this.mockMvc.perform(delete("/api/users/${id}")).andExpect(status().isAccepted).andReturn()

        val mvcGetResponse = this.mockMvc.perform(get("/api/users")).andExpect(status().isOk).andReturn()
        val users = mapper.readValue(mvcGetResponse.response.contentAsString, UserGetAllResponse::class.java)

        assertEquals(1, users.users.size)
        assertEquals("firstname3", users.users.get(0).firstname)
    }
}