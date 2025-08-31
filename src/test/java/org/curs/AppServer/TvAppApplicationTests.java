package org.curs.AppServer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        classes = TvApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class TvAppApplicationTests {

    @Test
    void contextLoads() {
        // проверка, что Spring Boot контекст поднимается
    }
}