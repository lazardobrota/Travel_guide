package com.example.backend.services;

import com.example.backend.entities.Article;
import com.example.backend.repo.IArticleRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    @Inject
    private IArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.getAllArticles();
    }
    public Article getArticleById(int id) {
        return articleRepository.getArticleById(id);
    }
    public Article addArticle(Article article) {
        return articleRepository.addArticle(article);
    }
    public void deleteArticle(int id) {
        articleRepository.deleteArticle(id);
    }
    public Article updateArticle(Article article) {
        return articleRepository.updateArticle(article);
    }
}
