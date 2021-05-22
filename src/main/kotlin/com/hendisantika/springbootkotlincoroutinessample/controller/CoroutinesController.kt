package com.hendisantika.springbootkotlincoroutinessample.controller

import com.hendisantika.springbootkotlincoroutinessample.Banner
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.time.LocalDateTime

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kotlin-coroutines-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/05/21
 * Time: 06.35
 */
@Controller
@RequestMapping("/coroutines")
class CoroutinesController(builder: WebClient.Builder) {

    private val banner =
        Banner("title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", LocalDateTime.now())

    private val client = builder.baseUrl("http://localhost:8080/coroutines").build()

    @GetMapping("/suspend")
    @ResponseBody
    suspend fun suspendingEndpoint(): Banner {
        delay(10)
        return banner
    }

    @GetMapping("/deferred")
    @ResponseBody
    fun deferredEndpointAsync(): Deferred<Banner> = GlobalScope.async {
        delay(10)
        banner
    }

    @GetMapping("/")
    suspend fun render(model: Model): String {
        delay(10)
        model["banner"] = banner
        return "index"
    }

    @GetMapping("/sequential-flow")
    @ResponseBody
    suspend fun sequentialFlow() = flow {
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
    }
}