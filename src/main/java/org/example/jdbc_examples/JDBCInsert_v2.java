package org.example.jdbc_examples;

import org.example.entity.StudentEntity;

import java.sql.*;

public class JDBCInsert_v2 {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/hibernate-demo";
    static final String USER = "postgres";
    static final String PWD = "postgres";

    public static void main(String[] args) {
        Connection connection = null;
        StudentEntity student = new StudentEntity("Loe", "Farrel", 8.4);

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);
            Statement statement = connection.createStatement();
            String sqlQuery = "INSERT INTO students (name, surname, avg_grade) VALUES" +
                "('" +
                student.getName() +
                "', '" +
                student.getSurname() +
                "'," +
                student.getAvgGrade() +
                ")";
            String sqlQuery2 = "INSERT INTO students (name, surname, avg_grade) VALUES ('Anna','Teremizova',6.6)";
            statement.executeUpdate(sqlQuery);
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
