package ru.yandex.practicum.filmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.dao.GenreStorage;
import ru.yandex.practicum.filmorate.dao.RatingStorage;
import ru.yandex.practicum.filmorate.exeption.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Rating;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmDbStorageTest {
    private final JdbcTemplate jdbcTemplate;

    @Test
    public void testFindFilmById() {
        // Подготавливаем данные для теста
        Film newFilm = new Film();
        Rating rating = new Rating();
        newFilm.setId(1);
        newFilm.setName("Avatar");
        newFilm.setDescription("nice");
        newFilm.setReleaseDate("2009-09-09");
        newFilm.setDuration(100);
        newFilm.setGenres(new ArrayList<>());
        rating.setId(2);
        rating.setName("PG");
        newFilm.setMpa(rating);
        FilmDbStorage filmStorage = new FilmDbStorage(jdbcTemplate, new RatingDbStorage(jdbcTemplate),
                new GenreDbStorage(jdbcTemplate));
        Film savedFilm = filmStorage.findFilm(1);

        assertThat(savedFilm)
                .isNotNull() // проверяем, что объект не равен null
                .usingRecursiveComparison() // проверяем, что значения полей нового
                .isEqualTo(newFilm);        // и сохраненного пользователя - совпадают
    }

    @Test
    public void testUpdateFilm() {
        FilmDbStorage filmStorage = new FilmDbStorage(jdbcTemplate, new RatingDbStorage(jdbcTemplate),
                new GenreDbStorage(jdbcTemplate));
        Film film = filmStorage.findFilm(2);
        Film updatedFilm = new Film();
        Rating rating = new Rating();
        updatedFilm.setId(2);
        updatedFilm.setName("New");
        updatedFilm.setDescription("n");
        updatedFilm.setReleaseDate("2009-09-09");
        updatedFilm.setDuration(111);
        updatedFilm.setGenres(new ArrayList<>());
        rating.setId(2);
        rating.setName("PG");
        updatedFilm.setMpa(rating);
        filmStorage.updateFilm(updatedFilm);

        Film savedFilm = filmStorage.findFilm(2);

        filmStorage.updateFilm(film);

        assertThat(savedFilm)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(updatedFilm);
    }

    @Test
    public void testDeleteFilm() {
        FilmDbStorage filmStorage = new FilmDbStorage(jdbcTemplate, new RatingDbStorage(jdbcTemplate),
                new GenreDbStorage(jdbcTemplate));
        Film updatedFilm = new Film();
        Rating rating = new Rating();
        updatedFilm.setId(4);
        updatedFilm.setName("Del");
        updatedFilm.setDescription("delete");
        updatedFilm.setReleaseDate("2019-09-19");
        updatedFilm.setDuration(1);
        rating.setId(2);
        rating.setName("PG");
        updatedFilm.setGenres(new ArrayList<>());
        updatedFilm.setMpa(rating);
        filmStorage.createFilm(updatedFilm);
        Film savedFilm = filmStorage.findFilm(4);
        filmStorage.deleteFilm(4);

        final FilmNotFoundException exception = assertThrows(
                FilmNotFoundException.class,
                new Executable() {
                    @Override
                    public void execute() {
                        Film film = filmStorage.findFilm(4);
                    }
                });

        assertThat(savedFilm)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(updatedFilm);
        assertEquals("Фильм не найден.", exception.getMessage());
    }

    @Test
    public void findAllFilms() {
        RatingStorage ratingStorage = new RatingDbStorage(jdbcTemplate);
        GenreStorage genreStorage = new GenreDbStorage(jdbcTemplate);
        FilmDbStorage filmStorage = new FilmDbStorage(jdbcTemplate, ratingStorage, genreStorage);
        Collection<Film> dbFilms = filmStorage.findAll();

        List<Film> films = new ArrayList<>(dbFilms);

        assertTrue(films.contains(filmStorage.findFilm(1)));
        assertTrue(films.contains(filmStorage.findFilm(2)));
        assertEquals(3, films.size());
    }
}