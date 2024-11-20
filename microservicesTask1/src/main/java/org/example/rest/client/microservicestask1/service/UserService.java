package org.example.rest.client.microservicestask1.service;


import org.example.rest.client.microservicestask1.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUserById(Long id);
    void updateUser(User user);
    User getUser(Long id);
    List<User> getAllUsers();
}