package com.hendisantika.springbootkotlincoroutinessample

import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.server.*
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

    // TODO Improve when https://github.com/Kotlin/kotlinx.coroutines/issues/1147 will be fixed
    suspend fun concurrentFlow(request: ServerRequest): ServerResponse = flow {
        for (i in 1..4) emit("/suspend")
    }.flatMapMerge {
        flow {
            emit(
                client
                    .get()
                    .uri(it)
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