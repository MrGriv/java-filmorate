package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping
@RestController
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 0;

    @GetMapping("/films")
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, Month.DECEMBER, 28))) {
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }

        film.setId(++id);

        log.trace("film: {}", film);

        films.put(film.getId(), film);
        return films.get(film.getId());
    }

    @PutMapping("/films")
    public Film putFilm(@Valid @RequestBody Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, Month.DECEMBER, 28))) {
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года");
        }

        log.trace("film: {}", film);

        if (films.containsKey(film.getId())) {
            films.replace(film.getId(), films.get(film.getId()), film);
        } else {
            throw new ValidationException("Нет такого фильма для обновления");
        }

        return films.get(film.getId());
    }
}
