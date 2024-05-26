package com.example.backend.repo;

import com.example.backend.entities.user.UserType;

public interface IUserTypeRepository {

    UserType getUserTypeById(int id);

    UserType addUserType(UserType userType);
}
