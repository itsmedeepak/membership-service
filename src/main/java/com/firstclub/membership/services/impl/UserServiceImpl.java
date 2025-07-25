package com.firstclub.membership.services.impl;

import com.firstclub.membership.models.User;
import com.firstclub.membership.repo.UserRepository;
import com.firstclub.membership.services.UserService;
import com.firstclub.membership.exceptions.ResourceNotFoundException;
import com.firstclub.membership.exceptions.InvalidRequestException;
import com.firstclub.membership.exceptions.DatabaseException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
        if (user == null) {
            throw new InvalidRequestException("User object cannot be null");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new InvalidRequestException("User name is required");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new InvalidRequestException("User email is required");
        }

        
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new InvalidRequestException("A user with this email already exists.");
        }
        try {
            return userRepository.save(user);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to save user. Please try again later.", ex);
        }
    }

    @Override
    public User getUserById(Long userId) {
        if (userId == null || userId <= 0) {
            throw new InvalidRequestException("Invalid user ID provided");
        }

        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        if (updatedUser == null) {
            throw new InvalidRequestException("Updated user data must not be null");
        }

        User existingUser = getUserById(userId);

        if (updatedUser.getName() != null && !updatedUser.getName().trim().isEmpty()) {
            existingUser.setName(updatedUser.getName());
        }

        if (updatedUser.getEmail() != null && !updatedUser.getEmail().trim().isEmpty()) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        try {
            return userRepository.save(existingUser);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to update user. Please try again later.", ex);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = getUserById(userId);
        try {
            userRepository.delete(existingUser);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to delete user. Please try again later.", ex);
        }
    }
}
