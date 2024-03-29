package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dao.FriendStorage;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final FriendStorage friendStorage;

    public void addFriend(int userId, int friendId) {
        friendStorage.addFriend(userId, friendId);
    }

    public void deleteFriend(int userId, int friendId) {
        friendStorage.deleteFriend(userId, friendId);
    }

    public List<User> getFriends(int id) {
        return friendStorage.getFriends(id);
    }

    public List<User> getCommonFriends(int userId, int friendId) {
        return friendStorage.getCommonFriends(userId, friendId);
    }
}