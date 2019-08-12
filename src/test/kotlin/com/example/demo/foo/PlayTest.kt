package com.example.demo.foo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.lang.RuntimeException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.function.Supplier
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class PlayTest {

    @Test
    fun inheritTest() {
        val f = Derived("name", 4)
        println("${f}")
        assertEquals(4, f.extra)
        assertEquals("name", f.message)

    }

    class MySupplier: Supplier<String> {
        override fun get(): String {
            try {
//                val x = Random.nextInt(10)
//                if (x > 5) {
//                    throw RuntimeException("Exception in supplier")
//                }
                Thread.sleep(300)
                return "foo"
            } catch (e: RuntimeException) {
                println("error in supplier")
            }
            return ""
        }
    }

    @Test
    fun futureTest() {
        val time = measureTimeMillis {
            val pool = Executors.newFixedThreadPool(9)
            val futures = mutableListOf<CompletableFuture<String>>()
            val n = 9
            for (i in 1..n) {
                val f = CompletableFuture.supplyAsync(MySupplier(), pool)
                futures.add(f)
            }
            val allCompletabeFuture = CompletableFuture.allOf(*futures.toTypedArray())
            val fs = allCompletabeFuture.thenApply({ f ->
                futures.map { f2 -> f2.join()}
            })
            val actual = fs.get()
            println(actual)
            val expected = (1..n).map { "foo"}
            assertEquals(expected, actual)
        }
        assertTrue(time < 350, "Time is no in range")
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