package com.cihexample.catalogservice.demo;


import com.cihexample.catalogservice.domain.Book;
import com.cihexample.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")    // 이 클래스는 testdata 프로파일이 활성화될 때만 로드 된다.
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 단계가 완료되면 발생한다.
    public void loadBookTestData(){
        var book1 = new Book("1234567891", "Northedn dfsd", "lydf sdf", 9.90);
        var book2 = new Book("1234567892", "woman life", "il fdfee", 12.90);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
