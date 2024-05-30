package com.example.backend.services;

import com.example.backend.entities.Article;
import com.example.backend.repo.IActivityArticleRepo;
import com.example.backend.repo.IArticleRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    @Inject
    private IArticleRepository articleRepository;

    @Inject
    private IActivityArticleRepo activityArticleRepo;


    public List<Article> getAllArticles() {
        return articleRepository.getAllArticles();
    }
    public Article getArticleById(int id) {

        Article article = articleRepository.getArticleById(id);
        article.setActivities(activityArticleRepo.getAllActivitiesForArticleId(id));

        return article;
    }
    public Article addArticle(Article article) {
        article = articleRepository.addArticle(article);

        if (article.getActivityIds() != null)
            activityArticleRepo.addAllConnections(article.getId(), article.getActivityIds());

        return article;
    }
    public void deleteArticle(int id) {
        articleRepository.deleteArticle(id);
    }
    public Article updateArticle(Article article) {
        return articleRepository.updateArticle(article);
    }
}
