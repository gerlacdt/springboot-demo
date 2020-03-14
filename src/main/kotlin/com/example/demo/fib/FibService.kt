package com.example.demo.fib

import org.springframework.stereotype.Service

@Service
class FibService {
    fun fib(n: Int): Int {
        if (n <= 0) {
            return n
        }
        var a = 1
        var b = 1
        for (i in 1 until n) {
            val tmp = a
            a = b
            b = tmp + b
        }
        return a
    }
}
