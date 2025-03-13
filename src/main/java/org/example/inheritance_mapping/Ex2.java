package org.example.inheritance_mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.inheritance_mapping.entity.Driver;
import org.example.inheritance_mapping.entity.Teacher;

public class Ex2 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Teacher teacher = new Teacher("Alessandro", 2500, 8d, "CS", true);
            Driver driver = new Driver("Peter", 2300, 15d, 'B', "BMW");
            entityManager.persist(teacher);
            entityManager.persist(driver);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
                factory.close();
            }
        }
    }
}
