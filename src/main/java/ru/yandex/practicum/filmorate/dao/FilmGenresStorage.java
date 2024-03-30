package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FilmGenresStorage {
    Map<Integer, List<Integer>> getGenresOfFilmsById(Collection<Film> films);

    void setFilmGenres(Film film);
}
