package com.example.backend.repo;

import com.example.backend.entities.user.User;

import java.util.List;

public interface IUserRepository {

     List<User> getAllUsers();
     User getUserByEmail(String email, boolean takePassword);

     User addUser(User user);

    void deleteUser(int id);

    User updateUser(User user);

    User getUserById(int id);
}
