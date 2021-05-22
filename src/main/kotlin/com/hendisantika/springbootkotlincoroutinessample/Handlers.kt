package com.hendisantika.springbootkotlincoroutinessample

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kotlin-coroutines-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/05/21
 * Time: 06.30
 */
@Suppress("DuplicatedCode")
@Component
class Handlers(builder: WebClient.Builder) {

    private val banner =
        Banner("title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", LocalDateTime.now())

    private val client = builder.baseUrl("http://localhost:8080").build()

}