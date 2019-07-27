package com.example.demo.fib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import java.net.URL


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FibHttpIntTest {

    @LocalServerPort
    var port: Int? = null

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun fibTest() {
        val base = URL("http://localhost:${port}/fib?n=10")
        val response = restTemplate.getForObject(base.toString(), FibResult::class.java)
        println("response: ${response}")
        assertEquals(FibResult(10, 55), response)
    }
}