package com.example.backend.repo;

import com.example.backend.entities.Comment;

import java.util.List;

public interface ICommentRepository {

    List<Comment> getAllCommentsForArticleById(int articleId);
    Comment addComment(Comment comment);
    void deleteComment(int id);
}
