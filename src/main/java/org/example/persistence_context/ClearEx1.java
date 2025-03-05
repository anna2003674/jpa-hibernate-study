package org.example.persistence_context;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.relationships.many_to_many.Teacher;

public class ClearEx1 {

    private static EntityManager entityManager;

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Teacher teacher1 = entityManager.find(Teacher.class, 4);
            Teacher teacher2 = entityManager.find(Teacher.class, 5);
            entityManager.clear(); // состояние detach (не внутри persistence context)
            teacher1.setProfessor(true);
            teacher2.setProfessor(true);
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
