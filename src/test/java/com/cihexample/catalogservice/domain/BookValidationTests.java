package com.cihexample.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validSuccesd(){
        var book = Book.of("1234567890", "title","AUthor", 9.90);
        Set<ConstraintViolation<Book>> validate = validator.validate(book);

        assertThat(validate).isEmpty();
    }

    @Test
    void when(){
        var book = Book.of("12345678df90", "title","AUthor", 9.90);
        Set<ConstraintViolation<Book>> validate = validator.validate(book);

        assertThat(validate).hasSize(1);
        assertThat(validate.iterator().next().getMessage()).isEqualTo("The isbn foramt be valid");

    }

}
