package com.hendisantika.springbootkotlincoroutinessample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringbootKotlinCoroutinesSampleApplicationTests(@Autowired val client: WebTestClient) {

    private val banner =
        Banner("title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", LocalDateTime.now())

    @Test
    fun index() {
        client.get().uri("/").exchange().expectStatus().is2xxSuccessful.expectBody()
    }


}
