package com.example.demo.quote

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("test")
class QuoteGatewayIntTest {

    @Autowired
    lateinit var gateway: QuoteGateway

    @Test
    fun getQuoteTest() {
        val quote = gateway.getQuote()

        assertEquals("success", quote?.type)
        assertNotNull(quote?.value?.quote)
        // println("$quote")
    }
}