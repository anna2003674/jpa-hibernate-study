package org.example;

import java.sql.*;

public class JDBCSelect {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/hibernate-demo";
    static final String USER = "postgres";
    static final String PWD = "postgres";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            String sqlQuery = "SELECT id, name, surname, avg_grade FROM students";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                double avgGrade = resultSet.getDouble("avg_grade");
                System.out.println("ID: " + id + ", Имя: " + name + ", Фамилия: " + surname + ", Средняя оценка: " + avgGrade);
            }
            resultSet.close();
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
