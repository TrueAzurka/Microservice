package org.example.rest.client.microservicestask1.dao;



import org.example.rest.client.microservicestask1.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUserById(Long id);
    void updateUser(User user);
    User getUser(Long id);
    List<User> getAllUsers();
}
