package com.example.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Comment {

    private int id;

    @NotNull(message = "Author field can't be null")
    @NotEmpty(message = "Author field can't be empty")
    private String author;

    @NotNull(message = "Text field can't be null")
    @NotEmpty(message = "Text field can't be empty")
    private String text;

    private LocalDate createdAt;

    @NotNull(message = "ArticleId field can't be null")
    private int articleId;

    public Comment(int id, String author, String text, LocalDate createdAt, int articleId) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
        this.articleId = articleId;
    }
}
