package com.example.demo

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
class Configuration {

    @Bean("threadPoolTaskExecutor")
    fun threadPoolTaskExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 10
        executor.maxPoolSize = 20
        executor.setQueueCapacity(500)
        executor.setThreadNamePrefix("my-spring-thread-exectuor-")
        executor.setBeanName("my-thread-executor")
        executor.initialize()
        println("foobar: ${executor}")
        return executor
    }

    @Bean
    fun javaTimeModule(): Module {
        return  JavaTimeModule()
    }

    @Bean
    fun jdk8Module(): Module {
        return  Jdk8Module()
    }
}