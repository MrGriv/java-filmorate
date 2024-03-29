package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dao.GenreStorage;
import ru.yandex.practicum.filmorate.dao.RatingStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Rating;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping
@RestController
public class FilmController {
    private final FilmStorage filmStorage;
    private final FilmService filmService;

    public FilmController(@Qualifier("filmDbStorage") FilmStorage filmStorage, FilmService filmService, GenreStorage genreStorage, RatingStorage ratingStorage) {
        this.filmStorage = filmStorage;
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public Collection<Film> findAll() {
        return filmStorage.findAll();
    }

    @GetMapping("/films/{id}")
    public Film findFilm(@PathVariable int id) {
        return filmStorage.findFilm(id);
    }

    @GetMapping("/films/popular")
    public Collection<Film> getPopularFilms(@RequestParam(defaultValue = "10", required = false, value = "count") int count) {
        return filmService.getPopularFilms(count);
    }

    @GetMapping("/genres")
    public Collection<Genre> getGenres() {
        return filmService.getGenres();
    }

    @GetMapping("/genres/{id}")
    public Genre getGenreById(@PathVariable int id) {
        return filmService.getGenreById(id);
    }

    @GetMapping("/mpa")
    public Collection<Rating> getRating() {
        return filmService.getRating();
    }

    @GetMapping("/mpa/{id}")
    public Rating getRatingById(@PathVariable int id) {
        return filmService.getRatingById(id);
    }

    @PostMapping(value = "/films")
    public Film create(@Valid @RequestBody Film film) {
        return filmStorage.createFilm(film);
    }

    @PutMapping("/films")
    public Film putFilm(@Valid @RequestBody Film film) {
        return filmStorage.updateFilm(film);
    }

    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable int id) {
        filmStorage.deleteFilm(id);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLike(id, userId);
    }
}