package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class FibControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun fibTest() {
        val mapper = ObjectMapper().registerModule(KotlinModule())
        val response = this.mockMvc.perform(get("/fib?n=10")).andExpect(status().isOk())
                .andReturn()
        val s = response.getResponse().getContentAsString()
        val fibResult = mapper.readValue(s, FibResult::class.java)
        println("response: $fibResult")


        val expected = FibResult(10, 55)
        assertEquals(expected, fibResult)
    }
}
