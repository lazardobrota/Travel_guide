package com.example.backend.repo.impl;

import com.example.backend.entities.Article;
import com.example.backend.filters.Global;
import com.example.backend.repo.IArticleRepository;
import com.example.backend.repo.MySqlRepo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository extends MySqlRepo implements IArticleRepository {
    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from article");

            while (resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getInt("destinationId"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getInt("visits"),
                        resultSet.getDate("createdAt").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
        }
        return articles;
    }

    @Override
    public Article getArticleById(int id) {
        Article article = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("select * from article where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            //TODO both list need to be added
            if (resultSet.next()) {
                article = new Article(
                        resultSet.getInt("id"),
                        resultSet.getInt("destinationId"),
                        resultSet.getString("author"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getInt("visits"),
                        resultSet.getDate("createdAt").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }
        return article;
    }

    @Override
    public Article addArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("INSERT INTO article (destinationId, author, title, text, visits, createdAt) VALUES (?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setInt(1, article.getDestinationId());
            preparedStatement.setString(2, article.getAuthor());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setString(4, article.getText());
            preparedStatement.setInt(5, article.getVisits());
            preparedStatement.setDate(6, Global.localDateToDate(LocalDate.now()));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return article;
    }

    @Override
    public void deleteArticle(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM article WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }
    }

    @Override
    public Article updateArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("update article " +
                    "set destinationId = ?, author = ?, title = ?, author = ?, text = ? where id = ?");
            preparedStatement.setInt(1, article.getDestinationId());
            preparedStatement.setString(2, article.getAuthor());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setString(4, article.getAuthor());
            preparedStatement.setString(5, article.getText());
            preparedStatement.setInt(6, article.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }

        return article;
    }
}
