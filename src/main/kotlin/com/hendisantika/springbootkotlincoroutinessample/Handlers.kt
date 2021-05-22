package com.hendisantika.springbootkotlincoroutinessample

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.renderAndAwait
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

    suspend fun index(request: ServerRequest) =
        ServerResponse
            .ok()
            .renderAndAwait("index", mapOf("banner" to banner))

    suspend fun suspending(request: ServerRequest) =
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(banner)

    suspend fun sequentialFlow(request: ServerRequest) = flow {
        for (i in 1..4) {
            emit(
                client
                    .get()
                    .uri("/suspend")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .awaitBody<Banner>()
            )
        }
    }.let {
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(it)
    }
}