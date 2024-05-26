package com.example.backend.entities.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class UserLogin {
    @NotNull(message = "Email field can't be null")
    @NotEmpty(message = "Email field can't be empty")
    @Email
    private String email;

    @NotNull(message = "Password field can't be null")
    @NotEmpty(message = "Password field can't be empty")
    private String password;
}
