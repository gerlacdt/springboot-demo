package com.example.demo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
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

@Bean
fun javaTimeModule(): Module {
     return  JavaTimeModule()
}

@Bean
fun jdk8Module(): Module {
    return  Jdk8Module()
}