package com.example.backend.repo.impl;

import com.example.backend.entities.Activity;
import com.example.backend.entities.ActivityArticle;
import com.example.backend.repo.IActivityArticleRepo;
import com.example.backend.repo.MySqlRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityArticleRepo extends MySqlRepo implements IActivityArticleRepo {
    @Override
    public List<Activity> getAllActivitiesForArticleId(int articleId) {
        List<Activity> activities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("select * from activity_on_article where  articleId= ?");
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

            List<Integer> activityIds = new ArrayList<>();
            StringBuilder sql = new StringBuilder();

            while (resultSet.next()) {
                int id = resultSet.getInt("activityId");
                activityIds.add(id);
                sql.append("?, "); //concating ? for amount of ids that will go in "id = in (?, ?, ?, ?, ...)"
            }

            //Get full activities based on given ids
            if (!activityIds.isEmpty()) {
                sql = new StringBuilder(sql.substring(0, sql.length() - 2)); //substring because it ends with "?," so "," needs to be removed

                preparedStatement = connection.prepareStatement("select * from activity where id in (" + sql  + ")");
                for (int i = 0; i < activityIds.size(); i++) {
                    preparedStatement.setInt(i + 1, activityIds.get(i));
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    activities.add(new Activity(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }
        return activities;
    }

    @Override
    public void addAllConnections(int articleId, List<Integer> activityIds) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            for (Integer activityId : activityIds) {
                preparedStatement = connection.prepareStatement("INSERT INTO activity_on_article (articleId, activityId) VALUES (?, ?)", generatedColumns);
                preparedStatement.setInt(1, articleId);
                preparedStatement.setInt(2, activityId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }
    }
}
