package com.example.demo

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@Tag("fast")
@SpringBootTest
class DummyTest  {

    @Test
    fun fooTest() {
        for (i in 1..10) {
            println("$i")
        }
    }

    @Test
    fun foo2Test() {
        for (i in 1..10) {
            println("$i")
        }
    }
}
