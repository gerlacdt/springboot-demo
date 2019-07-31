package com.example.demo.foo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayTest {

    @Test
    fun inheritTest() {
        val f = Derived("name", 4)
        println("${f}")
        assertEquals(4, f.extra)
        assertEquals("name", f.message)

    }
}


open class Base(val message: String) {

    override fun toString(): String {
        return "message: ${message}"
    }
}
class Derived(message: String, val extra: Int): Base(message) {

    override fun toString(): String {
        return "message: ${message} extra: ${extra}"
    }
}