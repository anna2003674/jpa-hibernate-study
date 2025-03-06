package org.example.persistence_context;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.relationships.many_to_many.Teacher;

public class MergeEx1 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Teacher teacher = new Teacher("Vera", "Walton", "Geography", true);
            entityManager.persist(teacher);

            transaction.commit();
            entityManager.close();

            teacher.setSubject("Math");

            entityManager = factory.createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            Teacher mergedTeacher = entityManager.merge(teacher);
            transaction.commit();
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
