package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private Validator validator;
    private final UserController userController = new UserController();

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void checkUserLoginValidation() {

        User user = User.builder()
                .id(1)
                .login("")
                .name("Nick Name")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(1946, Month.AUGUST, 20))
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void checkUserEmailValidation() {

        User user = User.builder()
                .id(1)
                .login("vasia")
                .name("Nick Name")
                .email("mailmail.ru@")
                .birthday(LocalDate.of(1946, Month.AUGUST, 20))
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void checkUserBirthdayValidation() {

        User user = User.builder()
                .id(1)
                .login("vasia")
                .name("Nick Name")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(2025, Month.AUGUST, 20))
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void userLoginMustBeWithoutBlancValidation() {
        User user = User.builder()
                .id(1)
                .login("Nick  Name")
                .name("vasia")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(2025, Month.AUGUST, 20))
                .build();

        assertThrows(ValidationException.class, () -> userController.create(user));
    }
}