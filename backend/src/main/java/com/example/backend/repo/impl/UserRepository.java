package com.example.backend.repo.impl;

import com.example.backend.entities.user.User;
import com.example.backend.filters.Global;
import com.example.backend.repo.IUserRepository;
import com.example.backend.repo.MySqlRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends MySqlRepo implements IUserRepository {

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user join usertype on user.userTypeId = usertype.id");

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getInt("userTypeId"),
                        resultSet.getBoolean("active"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email, boolean takePassword) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user join usertype on user.userTypeId = usertype.id where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getInt("userTypeId"),
                        resultSet.getBoolean("active"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("role")
                );

                if (takePassword)
                    user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("INSERT INTO user (email, userTypeId, active, password, name, lastname) VALUES (?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(2, user.getUserTypeId());
            preparedStatement.setBoolean(3, user.isActive());
            preparedStatement.setString(4, Global.hashPassword(user.getPassword()));
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getLastname());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return user;
    }

    @Override
    public void deleteUser(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
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
    public User updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("update user " +
                    "set email = ?, userTypeId = ?, active = ?,  name = ?, lastname = ? where id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(2, user.getUserTypeId());
            preparedStatement.setBoolean(3, user.isActive());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getLastname());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }

        return user;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user join usertype on user.userTypeId = usertype.id where user.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getInt("userTypeId"),
                        resultSet.getBoolean("active"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }
        return user;
    }
}
