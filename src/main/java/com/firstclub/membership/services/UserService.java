package com.firstclub.membership.services;

import com.firstclub.membership.models.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(Long userId, User updatedUser);

    void deleteUser(Long userId);
}
