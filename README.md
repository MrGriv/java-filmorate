# java-filmorate

![img.png](img.png)

**Получение всех фильмов:**

SELECT DISTINCT name, description, genre

FROM films

**Получение всех пользователей:**

SELECT DISTINCT name, login, email, birthday

FROM users

**Получение топ 50 фильмов:**

SELECT name

FROM films

WHERE film_id IN (SELECT film_id, COUNT(user_id)

FROM likes_from_users

GROUP BY film_id

ORDER BY COUNT(user_id) DESC

LIMIT 50)

**Получение списка общих друзей с пользователем 1:**

SELECT friend_id

FROM friends

WHERE user_id = 1
