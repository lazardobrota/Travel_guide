package com.example.backend.repo;

import java.sql.*;
import java.util.Optional;

public class MySqlRepo {

    public MySqlRepo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection newConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDatabaseName(), this.getUsername(), this.getPass()
        );
    }

    protected void closeConnection(Connection connection) {
        try {
            if (connection != null)
                Optional.of(connection).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeStatement(Statement statement) {
        try {
            if (statement != null)
                Optional.of(statement).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null)
                Optional.of(resultSet).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getHost() {return "localhost";}
    private int getPort() {return 3306;}
    private String getDatabaseName() {return "schema_travel_guide";}
    private String getUsername() {return "root";}
    private String getPass() {return "123456788";}
}
