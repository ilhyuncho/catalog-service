package com.cihexample.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Book (

    @Id
    Long id,
    @NotBlank(message="THe book isbn must be defined.")
            @Pattern(
                    regexp = "^([0-9]{10}|[0-9]{13})$",
                    message="The isbn foramt be valid"
            )
    String isbn,
    String title,
    String author,
    Double price,

    @CreatedDate
    Instant createdDate,
    @LastModifiedDate
    Instant lastModifiedDate,

    @Version    // 낙관적 잠금을 위해 사용 됨
    int version
){
    public static Book of(
            String isbn, String title, String author, Double price
    ){
        return new Book(null, isbn, title, author, price, null,null,0);
    }
}
