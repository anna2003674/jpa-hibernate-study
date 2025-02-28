package org.example;

import java.sql.*;

public class JDBCInsert {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/hibernate-demo";
    static final String USER = "postgres";
    static final String PWD = "postgres";

    public static void main(String[] args) {
        Connection connection = null;
        Student student = new Student("Chanel", "King", 9.1);

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);

            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO students(name, surname, avg_grade) VALUES (?,?,?)");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setDouble(3, student.getAvgGrade());
            statement.executeUpdate();
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
