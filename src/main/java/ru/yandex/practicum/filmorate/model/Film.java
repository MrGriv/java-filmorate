package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class Film {
    protected int id;
    @NotEmpty(message = "Имя не может быть пустым")
    protected String name;
    @Size(max = 200, message = "Максимальная длина описания — 200 символов")
    protected String description;
    protected LocalDate releaseDate;
    @Positive(message = "Продолжительность фильма должна быть положительным числом")
    protected int duration;
}
