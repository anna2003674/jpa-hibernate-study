package org.example.jpa_crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entity.Student;

public class Remove_ex {
    public static void main(String[] args) {
        EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        Student student = null;

        try {
            transaction.begin();
            student = entityManager.find(Student.class, 6);
            entityManager.remove(student);
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
