package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping
@RestController
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();
    private int id = 0;

    @GetMapping("/users")
    public Collection<User> findAll() {
        return users.values();
    }

    @PostMapping(value = "/users")
    public User create(@Valid @RequestBody User user) {

        if (user.getLogin().contains(" ")) {
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }

        user.setId(++id);

        log.trace("user: {}", user);

        users.put(user.getId(), user);

        if (users.get(user.getId()).getName() == null) {
            user.setName(user.getLogin());
            users.put(user.getId(), user);
        }

        return users.get(user.getId());
    }

    @PutMapping("/users")
    public User putUser(@Valid @RequestBody User user) {

        if (user.getLogin().contains(" ")) {
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }

        log.trace("user: {}", user);

        if (users.containsKey(user.getId())) {
            users.replace(user.getId(), users.get(user.getId()), user);

            if (users.get(user.getId()).getName() == null) {
                user.setName(user.getLogin());
                users.put(user.getId(), user);
            }

        } else {
            throw new ValidationException("Нет такого пользователя для обновления");
        }

        return users.get(user.getId());
    }
}
