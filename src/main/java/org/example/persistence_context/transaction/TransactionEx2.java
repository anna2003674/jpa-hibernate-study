package org.example.persistence_context.transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.relationships.many_to_many.Teacher;

public class TransactionEx2 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Teacher teacher1 = new Teacher("Anna", "Sidorova", "MIT", false);
            Teacher teacher2 = new Teacher("Sasha", "Ivanov", "Biology", true);

            entityManager.persist(teacher1);

            Teacher teacher3 = entityManager.find(Teacher.class, 100);
            System.out.println(teacher3.getName());

            entityManager.persist(teacher2);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ROLLBACK");
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
