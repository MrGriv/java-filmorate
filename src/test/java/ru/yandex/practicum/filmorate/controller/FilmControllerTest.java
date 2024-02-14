package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    private Validator validator;
    private final FilmController filmController = new FilmController();

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
                .releaseDate(LocalDate.of(1946, Month.AUGUST, 20))
                .duration(100)
                .build();

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void checkFilmDescriptionValidation() {

        StringBuilder description = new StringBuilder();

        description.append("1".repeat(201));

        Film film = Film.builder()
                .id(1)
                .name("A")
                .description(description.toString())
                .releaseDate(LocalDate.of(1946, Month.AUGUST, 20))
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
                .releaseDate(LocalDate.of(1946, Month.AUGUST, 20))
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
                .releaseDate(LocalDate.of(1746, Month.AUGUST, 20))
                .duration(100)
                .build();

        assertThrows(ValidationException.class, () -> filmController.create(film));
    }
}