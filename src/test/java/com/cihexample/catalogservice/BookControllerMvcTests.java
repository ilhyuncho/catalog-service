package com.cihexample.catalogservice;

import com.cihexample.catalogservice.common.exception.BookNotFoundException;
import com.cihexample.catalogservice.controller.BookController;
import com.cihexample.catalogservice.domain.BookService;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)       // 톰캣과 같은 서버를 로드 하지 않아도 된다.
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;    // 실제 구현 객체가 아닌 목 객체로 의존성 주입을 함

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "2829393939";

        given(bookService.viewBookDetails(isbn))
                .willThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }


}
































