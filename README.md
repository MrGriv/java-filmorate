# java-filmorate

![database.png](database.png)

**Получение всех фильмов:**

SELECT *

FROM FILMS

**Получение всех пользователей:**

SELECT *

FROM USERS

**Получение топ 50 фильмов:**

SELECT F.* FROM 

(SELECT FILM_ID 

FROM LIKES 

GROUP BY FILM_ID 

ORDER BY COUNT(USER_ID) DESC

LIMIT ?) AS L 

INNER JOIN FILMS AS F ON L.FILM_ID=F.FILM_ID

**Получение списка общих друзей пользователя 1 с пользователем 2:**

SELECT * FROM USERS WHERE USER_ID IN

(SELECT U.FRIEND_ID 

FROM (SELECT *

FROM FRIENDS 

WHERE USER_ID = 1) AS U 

INNER JOIN FRIENDS AS F ON F.FRIEND_ID = U.FRIEND_ID 

WHERE F.USER_ID = 2)
