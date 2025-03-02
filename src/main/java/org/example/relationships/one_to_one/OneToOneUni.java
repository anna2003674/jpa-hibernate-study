package org.example.relationships.one_to_one;

import jakarta.persistence.*;
import org.example.relationships.one_to_one.entity.Passport;
import org.example.relationships.one_to_one.entity.Student;

public class OneToOneUni {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Student student1 = new Student("Chanel", "King", 9.1);
            Passport passport1 = new Passport("chanel4@gmail.com", 174, "blue");
            student1.setPassport(passport1);
            entityManager.persist(passport1);
            entityManager.persist(student1);
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
