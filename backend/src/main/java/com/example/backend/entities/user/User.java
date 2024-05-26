package com.example.backend.entities.user;

import com.example.backend.entities.Article;
import com.example.backend.entities.Comment;
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

    private String role; //same as userTypeId only so it can return actual name of role and not id

    public User(int id, String email, int userTypeId, boolean active, String name, String lastname, String role) {
        this.id = id;
        this.email = email;
        this.userTypeId = userTypeId;
        this.active = active;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
    }

    public User(int id, String email, int userTypeId, boolean active, String password, String name, String lastname, List<Comment> comments, List<Article> articles) {
        this.id = id;
        this.email = email;
        this.userTypeId = userTypeId;
        this.active = active;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.comments = comments;
        this.articles = articles;
    }
}
