package com.example.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Article {

    private int id;

    @NotNull(message = "DestinationId field can't be null")
    private int destinationId;

    @NotNull(message = "Author field can't be null")
    @NotEmpty(message = "Author field can't be empty")
    private String author;

    @NotNull(message = "Title field can't be null")
    @NotEmpty(message = "Title field can't be empty")
    private String title;

    @NotNull(message = "Text field can't be null")
    @NotEmpty(message = "Text field can't be empty")
    private String text;

    @NotNull(message = "Visits field can't be null")
    private int visits;

    private LocalDate createdAt;

    private List<Comment> comments;

    private List<Activity> activities;

    public Article(int id, int destinationId, String author, String title, String text, int visits, LocalDate createdAt) {
        this.id = id;
        this.destinationId = destinationId;
        this.author = author;
        this.title = title;
        this.text = text;
        this.visits = visits;
        this.createdAt = createdAt;
    }
}
