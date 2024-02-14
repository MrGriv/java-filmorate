package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Builder
public class User {
    protected int id;
    @NotEmpty(message = "Почта не может быть пустой")
    @Email(message = "Почта не соответствует формату")
    protected String email;
    @NotEmpty(message = "Логин не может быть пустым")
    protected String login;
    protected String name;
    @PastOrPresent(message = "День рождения не может быть в будущем")
    protected LocalDate birthday;
}
