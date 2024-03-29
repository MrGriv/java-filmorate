package ru.yandex.practicum.filmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.dao.GenreStorage;
import ru.yandex.practicum.filmorate.dao.LikeStorage;
import ru.yandex.practicum.filmorate.dao.RatingStorage;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class LikeDbStorageTest {
    private final JdbcTemplate jdbcTemplate;

    @Test
    public void shouldDeleteLike() {
        RatingStorage ratingStorage = new RatingDbStorage(jdbcTemplate);
        GenreStorage genreStorage = new GenreDbStorage(jdbcTemplate);
        FilmStorage filmStorage = new FilmDbStorage(jdbcTemplate, ratingStorage, genreStorage);
        UserStorage userStorage = new UserDbStorage(jdbcTemplate);
        LikeStorage likeStorage = new LikeDbStorage(jdbcTemplate, filmStorage,userStorage,ratingStorage, genreStorage);


        likeStorage.deleteLike(3, 1);

        assertEquals(2, likeStorage.getPopularFilms(3).size());
        likeStorage.addLike(3, 1);
    }

    @Test
    public void shouldAddLike() {
        RatingStorage ratingStorage = new RatingDbStorage(jdbcTemplate);
        GenreStorage genreStorage = new GenreDbStorage(jdbcTemplate);
        FilmStorage filmStorage = new FilmDbStorage(jdbcTemplate, ratingStorage, genreStorage);
        UserStorage userStorage = new UserDbStorage(jdbcTemplate);
        LikeStorage likeStorage = new LikeDbStorage(jdbcTemplate, filmStorage,userStorage,ratingStorage, genreStorage);

        likeStorage.addLike(3, 2);

        assertEquals(3, likeStorage.getPopularFilms(3).size());
    }

    @Test
    public void shouldReturnMostPopularFilm() {
        RatingStorage ratingStorage = new RatingDbStorage(jdbcTemplate);
        GenreStorage genreStorage = new GenreDbStorage(jdbcTemplate);
        FilmStorage filmStorage = new FilmDbStorage(jdbcTemplate, ratingStorage, genreStorage);
        UserStorage userStorage = new UserDbStorage(jdbcTemplate);
        LikeStorage likeStorage = new LikeDbStorage(jdbcTemplate, filmStorage,userStorage,ratingStorage, genreStorage);

        assertEquals(1, likeStorage.getPopularFilms(1).size());
        assertTrue(likeStorage.getPopularFilms(1).contains(filmStorage.findFilm(1)));
    }
}