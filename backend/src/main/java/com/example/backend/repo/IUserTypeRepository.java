package com.example.backend.repo;

import com.example.backend.entities.user.UserType;

import java.util.List;

public interface IUserTypeRepository {

    UserType getUserTypeById(int id);

    UserType addUserType(UserType userType);

    List<UserType> getAllUserTypes();
}
