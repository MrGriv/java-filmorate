package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.Rating;

import java.util.Collection;

public interface RatingStorage {
    Rating getRatingById(int ratingId);

    Collection<Rating> getRating();
}
