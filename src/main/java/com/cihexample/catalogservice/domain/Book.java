package com.cihexample.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Book (
    @NotBlank(message="THe book isbn must be defined.")
            @Pattern(
                    regexp = "^([0-9]{10}|[0-9]{13})$",
                    message="The isbn foramt be valid"
            )
    String isbn,
    String title,
    String author,
    Double price
){}
