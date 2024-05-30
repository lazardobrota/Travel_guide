package com.example.backend.controller;

import com.example.backend.entities.Article;
import com.example.backend.services.ArticleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/article")
public class ArticleController {

    @Inject
    private ArticleService articleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArticles() {
        return Response.ok(articleService.getAllArticles()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticleById(@PathParam("id") int id) {
        return articleService.getArticleById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteArticle(@PathParam("id") int id) {
        articleService.deleteArticle(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Article addArticle(Article article) {
        return articleService.addArticle(article);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Article updateArticle(Article article) {
        return articleService.updateArticle(article);
    }
}