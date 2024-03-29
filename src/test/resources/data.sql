INSERT INTO USERS (EMAIL, LOGIN, USER_NAME, BIRTHDAY)
VALUES ('user@email.ru', 'vanya123', 'Ivan Petrov', '1990-01-01'),
       ('iv@ya.ru', 'rast', 'Vasia', '1999-09-09'),
       ('z@xiz.ru', 'Luk', 'Eva', '2022-02-02');

INSERT INTO FILMS (FILM_NAME, DESCRIPTION, RELEASE_DATE, DURATION, RATING_ID)
VALUES ( 'Avatar', 'nice', '2009-09-09', 100, 2),
       ('Moon', 'Normal', '2012-12-30', 50, 1),
       ( 'Play', 'boring', '1999-07-07', 33, 5);

MERGE INTO RATINGS (RATING_ID, RATING_NAME) VALUES (1,'G'),
                                                   (2, 'PG'),
                                                   (3, 'PG-13'),
                                                   (4, 'R'),
                                                   (5, 'NC-17');

MERGE INTO GENRES (GENRE_ID, GENRE_NAME) VALUES (1, 'Комедия'),
                                                (2, 'Драма'),
                                                (3, 'Мультфильм'),
                                                (4, 'Триллер'),
                                                (5, 'Документальный'),
                                                (6, 'Боевик');

INSERT INTO FRIENDS (USER_ID, FRIEND_ID)
VALUES (1, 3),
       (2, 3);

INSERT INTO LIKES (FILM_ID, USER_ID) VALUES (1, 1),
                                            (1, 2),
                                            (1, 3),
                                            (2, 1),
                                            (2, 2),
                                            (3, 1);