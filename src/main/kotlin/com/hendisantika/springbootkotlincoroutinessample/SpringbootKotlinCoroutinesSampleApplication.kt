package com.hendisantika.springbootkotlincoroutinessample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.server.coRouter
import java.time.LocalDateTime

@SpringBootApplication
class SpringbootKotlinCoroutinesSampleApplication {
    @Bean
    fun routes(handlers: Handlers) = coRouter {
        GET("/", handlers::index)
        GET("/suspend", handlers::suspending)
        GET("/sequential-flow", handlers::sequentialFlow)
        GET("/concurrent-flow", handlers::concurrentFlow)
        GET("/error", handlers::error)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringbootKotlinCoroutinesSampleApplication>(*args)
}

data class Banner(val title: String, val message: String, val date: LocalDateTime)
