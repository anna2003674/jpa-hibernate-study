package org.example.relationships.many_to_many;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class ManyToManyBi {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            University university = new University("Harvard",
                Date.valueOf("1636-10-28"));
            Teacher teacher1 = new Teacher("Alessandro", "Lozano", "CS", true);
            Teacher teacher2 = new Teacher("Rio", "Berger", "Biology", false);
            Teacher teacher3 = new Teacher("Landry", "Shelton", "Math", true);
            university.addTeacherToUniversity(teacher1);
            university.addTeacherToUniversity(teacher2);
            university.addTeacherToUniversity(teacher3);
            entityManager.persist(university);
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
