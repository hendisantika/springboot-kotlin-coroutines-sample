package com.hendisantika.springbootkotlincoroutinessample.controller

import com.hendisantika.springbootkotlincoroutinessample.Banner
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.test.web.reactive.server.expectBodyList
import java.time.LocalDateTime

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kotlin-coroutines-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/05/21
 * Time: 06.58
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CoroutinesControllerTests(@Autowired val client: WebTestClient) {
    private val banner =
        Banner("title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", LocalDateTime.now())

    @Test
    fun index() {
        client.get().uri("/coroutines/").exchange().expectStatus().is2xxSuccessful.expectBody()
    }

    @Test
    fun suspending() {
        client.get()
            .uri("/coroutines/suspend")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody<Banner>()
            .isEqualTo(banner)
    }

    @Test
    fun sequentialFlow() {
        client.get()
            .uri("/controller/sequential-flow")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBodyList<Banner>().contains(banner, banner, banner, banner)
    }

    @Test
    fun parallelFlow() {
        client.get()
            .uri("/controller/concurrent-flow")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBodyList<Banner>().contains(banner, banner, banner, banner)
    }


    @Test
    fun flowViaWebClient() {
        client.get()
            .uri("/controller/flow-via-webclient")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBodyList<Banner>().contains(banner, banner, banner, banner)
    }
}
