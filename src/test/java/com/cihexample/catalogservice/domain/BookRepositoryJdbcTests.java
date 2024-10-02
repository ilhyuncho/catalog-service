package com.cihexample.catalogservice.domain;

import com.cihexample.catalogservice.config.DataConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest   // 스프링 데이터 JDBC 컴포넌트를 집중적으로 테스트하는 클래스임을 나타냄
@Import(DataConfig.class)   // 감사를 활성화 하기 위해
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE    // 테스트컨테이너를 이용해야 하기 때문에
                                                            // 내장 테스트 데이터베이스 사용을 비활성화
)
@ActiveProfiles("integration")  // application-integration.yml 을 적용
class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;    // 데이터베이스와 상호작용하기 위한 하위 수준의 객체

    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234561237";
        var book = Book.of(bookIsbn, "Title", "Author", 12.90, "pub9");
        jdbcAggregateTemplate.insert(book);

        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(bookIsbn);
    }



}
