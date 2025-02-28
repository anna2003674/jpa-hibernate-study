package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUpdate {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/hibernate-demo";
    static final String USER = "postgres";
    static final String PWD = "postgres";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            String sql = "UPDATE students SET avg_grade = ? WHERE surname = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, 9.5);
            statement.setString(2, "Farrel");

            int updatedRows = statement.executeUpdate();
            System.out.println("Обновлено строк: " + updatedRows);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
