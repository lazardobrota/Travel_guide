package com.example.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private int id;

    @NotNull(message = "Email field can't be null")
    @NotEmpty(message = "Email field can't be empty")
    @Email
    private String email;

    @NotNull(message = "UserType field can't be null")
    private int userTypeId;

    @NotNull(message = "Active field can't be null")
    private boolean active;

    @NotNull(message = "Password field can't be null")
    @NotEmpty(message = "Password field can't be empty")
    private String password;

    @NotNull(message = "Name field can't be null")
    @NotEmpty(message = "Name field can't be empty")
    private String name;

    @NotNull(message = "Lastname field can't be null")
    @NotEmpty(message = "Lastname field can't be empty")
    private String lastname;

    private List<Comment> comments;

    private List<Article> articles;
}
