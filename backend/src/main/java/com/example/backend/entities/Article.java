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
public class Article {

    private int id;

    @NotNull(message = "DestinationId field can't be null")
    private int destinationId;

    @NotNull(message = "AuthorId field can't be null")
    private int authorId;

    @NotNull(message = "Title field can't be null")
    @NotEmpty(message = "Title field can't be empty")
    private String title;

    @NotNull(message = "Text field can't be null")
    @NotEmpty(message = "Text field can't be empty")
    private String text;

    @NotNull(message = "Text field can't be null")
    private int visits;

    @NotNull(message = "Text field can't be null")
    private LocalDate createdAt;
}
