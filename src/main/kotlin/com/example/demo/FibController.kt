package com.example.demo

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FibController(val fibService: FibService) {

    @RequestMapping("/fib")
    fun fib(@RequestParam(value="n", defaultValue = "10") n: Int): FibResult {
        return FibResult(n=n, result=fibService.fib(n))
    }
}