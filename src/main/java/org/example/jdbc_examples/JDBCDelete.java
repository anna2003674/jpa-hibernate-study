package org.example.jdbc_examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDelete {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/hibernate-demo";
    static final String USER = "postgres";
    static final String PWD = "postgres";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            PreparedStatement statement =
                connection.prepareStatement("DELETE FROM students WHERE surname = ?");
            statement.setString(1, "Teremizova");
            int deletedRows = statement.executeUpdate();

            System.out.println("deletedRows = " + deletedRows);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
