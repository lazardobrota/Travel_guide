package com.example.backend.repo;

import com.example.backend.entities.Article;

import java.util.List;

public interface IArticleRepository {

    List<Article> getAllArticles();
    Article getArticleById(int id);
    Article addArticle(Article article);
    void deleteArticle(int id);
    Article updateArticle(Article article);

    void addVisit(int id);
}
