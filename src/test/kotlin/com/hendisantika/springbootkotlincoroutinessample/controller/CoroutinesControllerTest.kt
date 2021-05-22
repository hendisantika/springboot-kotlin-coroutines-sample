package com.hendisantika.springbootkotlincoroutinessample.controller

import com.hendisantika.springbootkotlincoroutinessample.Banner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
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

}
