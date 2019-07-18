package com.example.demo

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController(val fibService: FibService) {

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value="name", defaultValue="World") name: String): Greeting {
        return Greeting(id=5, content=name)
    }

    @RequestMapping("/fib")
    fun fib(@RequestParam(value="n") n: Int): FibResult {
        return FibResult(n=n, result=fibService.fib(n))
    }
}