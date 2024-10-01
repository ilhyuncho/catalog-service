package com.cihexample.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

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
    @Version    // 낙관적 잠금을 위해 사용 됨
    int version
){
    public static Book of(
            String isbn, String title, String author, Double price
    ){
        return new Book(null, isbn, title, author, price, 0);
    }
}
