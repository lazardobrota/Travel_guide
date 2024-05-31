package com.example.backend.repo;

import com.example.backend.entities.Article;
import com.example.backend.entities.Comment;

import java.util.List;

public interface ICommentRepository {

    List<Comment> getAllComments();
    Comment addComment(Comment comment);
    void deleteComment(int id);
}
