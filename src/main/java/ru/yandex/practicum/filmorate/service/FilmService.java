package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.GenreStorage;
import ru.yandex.practicum.filmorate.dao.LikeStorage;
import ru.yandex.practicum.filmorate.dao.RatingStorage;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Rating;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final LikeStorage likeStorage;
    private final GenreStorage genreStorage;
    private final RatingStorage ratingStorage;

    public void addLike(int filmId, int userId) {
        likeStorage.addLike(filmId, userId);
    }

    public void deleteLike(int filmId, int userId) {
        likeStorage.deleteLike(filmId, userId);
    }

    public Collection<Film> getPopularFilms(int count) {
        return likeStorage.getPopularFilms(count);
    }

    public Collection<Rating> getRating() {
        return ratingStorage.getRating();
    }
    public Rating getRatingById(int ratingId) {
        return ratingStorage.getRatingById(ratingId);
    }

    public Collection<Genre> getGenres() {
        return genreStorage.getGenres();
    }

    public Genre getGenreById(int genreId) {
        return genreStorage.getGenreById(genreId);
    }
}