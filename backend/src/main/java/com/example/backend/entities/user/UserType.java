package com.example.backend.entities.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserType {

    private int id;

    @NotNull(message = "Role field can't be null")
    @NotEmpty(message = "Role field can't be empty")
    private String role;

    public UserType(int id, String role) {
        this.id = id;
        this.role = role;
    }
}
