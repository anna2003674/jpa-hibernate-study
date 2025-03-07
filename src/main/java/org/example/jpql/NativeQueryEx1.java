package org.example.jpql;

import jakarta.persistence.*;
import org.example.jpql.entity.Student;

import java.util.List;

public class NativeQueryEx1 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            Query query = entityManager.createNativeQuery("select*from students", Student.class);
            List<Student> students = query.getResultList();
            System.out.println(students);

            Query query2 = entityManager.createNativeQuery("select*from students where avg_grade > ?1 and " +
                "length(name) = ?2", Student.class);
            query2.setParameter(1, 8);
            query2.setParameter(2, 5);
            List<Student> students2 = query2.getResultList();
            System.out.println(students2);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
                factory.close();
            }
        }
    }
}
