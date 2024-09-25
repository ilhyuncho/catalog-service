package com.cihexample.catalogservice;

import com.cihexample.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest       // 도메인 객체에 대한 json 직렬화 및 역직렬화를 테스트 할수 있다
                // @JsonTest는 스프링 애플리케이션 콘텍스트를 로드하고 사용 중인 특정 라이브러리에 대한 JSON매퍼를 자동으로 구성
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;       // JSON 직렬화 및 역직렬화를 확인하기 위한 유틸리티 클래스

    @Test
    void testSerialize() throws Exception{
        var book = new Book("1234567891", "Title","Author",3.333);

        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
    }

    @Test
    void testDeserialize() throws Exception{
        var content = """       
                {
                    "isbn":"1234567890",
                    "title":"Title",
                    "author":"Author",
                    "price":9.90
                }
                """;

        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Title","Author",9.90));
    }
}
