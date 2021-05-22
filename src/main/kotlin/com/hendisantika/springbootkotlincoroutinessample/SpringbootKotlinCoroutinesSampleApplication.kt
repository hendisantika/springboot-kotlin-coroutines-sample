package com.hendisantika.springbootkotlincoroutinessample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

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
