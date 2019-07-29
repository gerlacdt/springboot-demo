package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.zalando.logbook.BodyFilter
import org.zalando.logbook.BodyFilter.merge
import org.zalando.logbook.BodyFilters.defaultValue

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

