package com.example.backend.repo.impl;

import com.example.backend.entities.Comment;
import com.example.backend.filters.Global;
import com.example.backend.repo.ICommentRepository;
import com.example.backend.repo.MySqlRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository extends MySqlRepo implements ICommentRepository {
    @Override
    public List<Comment> getAllCommentsForArticleById(int articleId) {
        List<Comment> comments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("select * from comment where articleId = ?");
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comments.add(new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("author"),
                        resultSet.getString("text"),
                        resultSet.getDate("createdAt").toLocalDate(),
                        resultSet.getInt("articleId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return comments;
    }

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("INSERT INTO comment (text, author, createdAt, articleId) VALUES (?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setString(2, comment.getAuthor());
            preparedStatement.setDate(3, Global.localDateToDate(LocalDate.now()));
            preparedStatement.setInt(4, comment.getArticleId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return comment;
    }

    @Override
    public void deleteComment(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM comment WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }
    }
}
