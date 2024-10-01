package com.cihexample.catalogservice;

import com.cihexample.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenBookCreated(){
        var expectedBook = Book.of("1234567891", "Title","Author",3.333);

        webTestClient.post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()         // 요청을 전송
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook ->{
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
                });
    }

    @Test
    void contextLoads() {
    }

}
