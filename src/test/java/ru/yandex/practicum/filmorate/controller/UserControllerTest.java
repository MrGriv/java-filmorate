package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

class UserControllerTest {
    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

/*    @Test
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
                .login("Nic Name")
                .name("vasia")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(2020, Month.AUGUST, 20))
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }*/
}