package com.example.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ActivityArticle {

    private int id;

    @NotNull(message = "ArticleId field can't be null")
    private int articleId;

    @NotNull(message = "ActivityId field can't be null")
    private int activityId;

    public ActivityArticle(int id, int articleId, int activityId) {
        this.id = id;
        this.articleId = articleId;
        this.activityId = activityId;
    }
}
