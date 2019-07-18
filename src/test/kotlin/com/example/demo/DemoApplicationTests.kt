package com.example.demo


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	lateinit var fibService: FibService

	@Test
	fun contextLoads() {
		val x = 1
		assertEquals(1, x)
	}


	@Test
	fun fibTest() {
		for (i in 1..11) {
			println("fib($i) = ${fibService.fib(i)}")
		}
	}

}
