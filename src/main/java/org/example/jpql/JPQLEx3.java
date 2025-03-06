package org.example.jpql;

import jakarta.persistence.*;
import org.example.jpql.entity.Student;

import java.util.List;

public class JPQLEx3 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("select s from Student s" +
                " where s.name LIKE ?1 and s.avgGrade > ?2");
            query.setParameter(1, "%l%");
            query.setParameter(2, 8.5);
            List<Student> students = query.getResultList();
            System.out.println(students);

            Query query2 = entityManager.createQuery("select s from Student s" +
                " where s.name LIKE :letter and s.avgGrade > :grade ");
            query2.setParameter("letter", "%l%");
            query2.setParameter("grade", 8.5);
            List<Student> students2 = query.getResultList();
            System.out.println(students2);

            Query query3 = entityManager.createQuery("UPDATE Student s SET avgGrade = 7.0" +
                " where length(surname)>6 ");
            query3.executeUpdate();

            Query query4 = entityManager.createQuery("DELETE Student s WHERE s.avgGrade < 7.5 OR s.avgGrade IS NULL");
            query4.executeUpdate();
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
