package com.example.demo.fib

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(FibController::class)
@ActiveProfiles("test")
class FibControllerIntTest(@Autowired val mockMvc: MockMvc,
                           @Autowired val mapper: ObjectMapper) {

    @MockkBean
    lateinit var fibService: FibService

    @Test
    fun fibTest() {
        every { fibService.fib(10) } returns 55
        val response = this.mockMvc.perform(get("/api/fib?n=10")).andExpect(status().isOk())
                .andReturn()
        val s = response.getResponse().getContentAsString()
        val fibResult = mapper.readValue(s, FibResult::class.java)
        println("response: $fibResult")


        val expected = FibResult(10, 55)
        assertEquals(expected, fibResult)
    }
}