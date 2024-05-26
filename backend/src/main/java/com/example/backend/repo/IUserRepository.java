package com.example.backend.repo;

import com.example.backend.entities.user.User;

import java.util.List;

public interface IUserRepository {

     List<User> getAllUsers();
     User getUserByEmail(String email);

     User addUser(User user);
}
