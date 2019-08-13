package com.example.demo.github

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.CompletableFuture
import kotlin.system.measureTimeMillis

@SpringBootTest
@ActiveProfiles("test")
class GithubGatewayIntTest {

    @Autowired
    lateinit var gateway: GithubGateway

    @Test
    fun getGithubUserTest() {
        val future = gateway.getUserInfo("gerlacdt")
        println(future.get())
    }

    @Test
    fun concurrencyTest() {
        val time = measureTimeMillis {
            val futures = mutableListOf<CompletableFuture<GithubUser?>>()
            val n = 11
            for (i in 1..n) {
                futures.add(gateway.getUserInfo(("gerlacdt")))
            }
            val allCompletabeFuture = CompletableFuture.allOf(*futures.toTypedArray())
            val fs = allCompletabeFuture.thenApply({ f ->
                futures.map { f2 -> f2.join() }
            })
            val actual = fs.get()
            println(actual)
        }
        println(time)
    }

}