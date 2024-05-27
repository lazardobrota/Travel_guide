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

    @NotNull(message = "Text field can't be null")
    @NotEmpty(message = "Text field can't be empty")
    private String text;

    @NotNull(message = "CreatedAt field can't be null")
    private LocalDate createAt; //todo add converter

    @NotNull(message = "ArticleId field can't be null")
    private int articleId;
}
