package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void checkFilmNameValidation() {

        Film film = Film.builder()
                .id(1)
                .name("")
                .description("abc")
                .releaseDate("1895-12-29")
                .duration(100)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void checkFilmDescriptionValidation() {

        Film film = Film.builder()
                .id(1)
                .name("A")
                .description("1".repeat(201))
                .releaseDate("1895-12-29")
                .duration(100)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void checkFilmDurationValidation() {

        Film film = Film.builder()
                .id(1)
                .name("A")
                .description("abc")
                .releaseDate("1895-12-29")
                .duration(0)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void checkFilmReleaseDateValidation() {

        Film film = Film.builder()
                .id(1)
                .name("A")
                .description("abc")
                .releaseDate("1795-12-29")
                .duration(100)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }
}