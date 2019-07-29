package com.example.demo.fib

import net.logstash.logback.marker.Markers.append
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FibController(val fibService: FibService) {

    val logger: Logger = LoggerFactory.getLogger(FibController::class.java)

    @RequestMapping("/api/fib")
    fun fib(@RequestParam(value="n", defaultValue = "10") n: Int): FibResult {
        val result = FibResult(n = n, result = fibService.fib(n))
        logger.info(append("result", result), "http request: fib($n) ")
        return result
    }
}