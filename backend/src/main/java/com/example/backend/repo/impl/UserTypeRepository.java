package com.example.backend.repo.impl;

import com.example.backend.entities.user.UserType;
import com.example.backend.repo.IUserTypeRepository;
import com.example.backend.repo.MySqlRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTypeRepository extends MySqlRepo implements IUserTypeRepository {
    @Override
    public UserType getUserTypeById(int id) {
        UserType userType = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM usertype where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userType = new UserType(
                        id,
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
        return userType;
    }

    @Override
    public UserType addUserType(UserType userType) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("INSERT INTO usertype (role) VALUES (?)", generatedColumns);
            preparedStatement.setString(1, userType.getRole());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                userType.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return userType;
    }

    @Override
    public List<UserType> getAllUserTypes() {
        List<UserType> userTypes = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from usertype");

            while (resultSet.next()) {
                userTypes.add(new UserType(
                   resultSet.getInt("id"),
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
        return userTypes;
    }
}
