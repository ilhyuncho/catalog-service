package com.cihexample.catalogservice.demo;


import com.cihexample.catalogservice.domain.Book;
import com.cihexample.catalogservice.domain.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="polar.testdata2.enabled", havingValue = "true")
public class BookData2Loader {
    private final BookRepository bookRepository;

    public BookData2Loader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadBookTestData(){
        // BookData2Loader, BookDataLoader 중 BookData2Loader 이 먼저 실행 됨

//        var book1 = new Book("1234567893", "Northedn dfsd2", "lydf sdf2", 9.90);
//        var book2 = new Book("1234567894", "woman life2", "il fdfee2", 12.90);

        var book1 = Book.of("1234567893", "Northedn dfsd2", "lydf sdf2", 9.90,"pub1");
        var book2 = Book.of("1234567894", "woman life2", "il fdfee2", 12.90, "pub2");

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
