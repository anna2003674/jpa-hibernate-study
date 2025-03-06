package org.example.jpql;

import jakarta.persistence.*;
import org.example.jpql.entity.University;

import java.util.List;

public class JPQLEx4 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("SELECT u FROM University u WHERE u.students IS EMPTY");
            List<University> universities = query.getResultList();
            System.out.println(universities);

            Query query2 = entityManager.createQuery("SELECT u FROM University u WHERE SIZE(u.students) = 1");
            List<University> universities2 = query2.getResultList();
            System.out.println(universities2);

            Query query3 = entityManager.createQuery("SELECT u FROM University u ORDER BY SIZE(u.students) DESC");
            List<University> universities3 = query3.getResultList();
            System.out.println(universities3);

            // CROSS JOIN
            Query query4 = entityManager.createQuery("SELECT u, s FROM University u, Student s");
            List<Object[]> results = query4.getResultList();
            for (Object[] result : results) {
                System.out.println(result[0] + " " + result[1]);
            }
            System.out.println("------------------------");

            Query query5 = entityManager.createQuery("SELECT u, s FROM University u JOIN u.students s");
            List<Object[]> results5 = query5.getResultList();
            for (Object[] result : results5) {
                System.out.println(result[0] + " " + result[1]);
            }
            System.out.println("-----------");

            // LEFT JOIN
            Query query6 = entityManager.createQuery("SELECT u, s FROM University u LEFT JOIN u.students s");
            List<Object[]> results6 = query6.getResultList();
            for (Object[] result : results6) {
                System.out.println(result[0] + " " + result[1]);
            }
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
