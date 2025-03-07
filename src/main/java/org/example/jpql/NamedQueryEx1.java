package org.example.jpql;

import jakarta.persistence.*;
import org.example.jpql.entity.Student;
import org.example.jpql.entity.University;

import java.util.List;

public class NamedQueryEx1 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            Query query = entityManager.createNamedQuery("University.allUniversitiesLessOrEqualsTo2");
            List<University> universities = query.getResultList();
            System.out.println(universities);

            Query query2 = entityManager.createNamedQuery("University.studentsWithAvgGradeBetween");
            query2.setParameter("from", 6);
            query2.setParameter("to", 8);
            List<Student> students = query.getResultList();
            System.out.println(students);

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
