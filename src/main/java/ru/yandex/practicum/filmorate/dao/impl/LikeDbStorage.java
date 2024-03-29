package ru.yandex.practicum.filmorate.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.GenreStorage;
import ru.yandex.practicum.filmorate.dao.LikeStorage;
import ru.yandex.practicum.filmorate.dao.RatingStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.*;

@Component
public class LikeDbStorage implements LikeStorage {
    private final JdbcTemplate jdbcTemplate;
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;
    private final RatingStorage ratingStorage;
    private final GenreStorage genreStorage;

    @Autowired
    public LikeDbStorage(JdbcTemplate jdbcTemplate,
                         @Qualifier("filmDbStorage") FilmStorage filmStorage,
                         @Qualifier("userDbStorage") UserStorage userStorage,
                         RatingStorage ratingStorage, GenreStorage genreStorage) {
        this.jdbcTemplate = jdbcTemplate;
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.ratingStorage = ratingStorage;
        this.genreStorage = genreStorage;
    }

    @Override
    public void addLike(int filmId, int userId) {
        filmStorage.findFilm(filmId);
        userStorage.findUser(userId);

        String sql = "INSERT INTO LIKES (FILM_ID, USER_ID) VALUES (?, ?)";
        jdbcTemplate.update(sql, filmId, userId);
    }

    @Override
    public void deleteLike(int filmId, int userId) {
        filmStorage.findFilm(filmId);
        userStorage.findUser(userId);

        String sql = "DELETE FROM LIKES WHERE FILM_ID = ? AND USER_ID = ?";
        jdbcTemplate.update(sql, filmId, userId);
    }

    @Override
    public Collection<Film> getPopularFilms(int count) {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT F.* FROM " +
                "(SELECT FILM_ID " +
                "FROM LIKES " +
                "GROUP BY FILM_ID " +
                "ORDER BY COUNT(USER_ID) DESC " +
                "LIMIT ?) AS L " +
                "INNER JOIN FILMS AS F ON L.FILM_ID=F.FILM_ID";
        SqlRowSet filmRows = jdbcTemplate.queryForRowSet(sql, count);

        while (filmRows.next()) {
            Film film = new Film ();

            film.setId(filmRows.getInt("FILM_ID"));
            film.setName(filmRows.getString("FILM_NAME"));
            film.setDescription(filmRows.getString("DESCRIPTION"));
            film.setReleaseDate(filmRows.getDate("RELEASE_DATE").toString());
            film.setDuration(filmRows.getInt("DURATION"));
            film.setMpa(ratingStorage.getRatingById(filmRows.getInt("RATING_ID")));

            SqlRowSet genreRows = jdbcTemplate.queryForRowSet("SELECT * FROM FILM_GENRES WHERE FILM_ID = ?",
                    filmRows.getInt("FILM_ID"));
            List<Genre> genres = new ArrayList<>();
            while (genreRows.next()) {
                genres.add(genreStorage.getGenreById(genreRows.getInt("GENRE_ID")));
            }
            film.setGenres(genres);
            films.add(film);
        }
        return films;
    }
}
