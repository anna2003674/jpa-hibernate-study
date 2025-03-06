package org.example.jpql;

import jakarta.persistence.*;
import org.example.jpql.entity.Student;

import java.util.List;

public class JPQLEx2 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            List<Student> student1 = entityManager.createQuery("select s from Student s" +
                " where s.name like '%a%' ").getResultList();
            System.out.println(student1);

            List<Student> student2 = entityManager.createQuery("select s from Student s" +
                " where lower(s.name) like '%a%'").getResultList();
            System.out.println(student2);

            List<Student> student3 = entityManager.createQuery("select s from Student s" +
                " where avgGrade is null").getResultList();
            System.out.println(student3);

            List<Student> student4 = entityManager.createQuery("select s from Student s" +
                " where s.name like '%l%' and s.avgGrade>8.5").getResultList();
            System.out.println(student4);

            List<Student> names = entityManager.createQuery("select s.name from Student s").getResultList();
            System.out.println(names);

            List<Object[]> studentsInfo = entityManager.createQuery("select s.name, s.avgGrade from Student s").getResultList();
            for (Object[] info : studentsInfo) {
                System.out.println(info[0] + " " + info[1]);
            }

            Query query = entityManager.createQuery("select max(s.avgGrade) from Student s");
            double maxGrade = (Double) query.getSingleResult();
            System.out.println(maxGrade);

            Query query2 = entityManager.createQuery("select avg(s.avgGrade) from Student s");
            double avgGrade = (Double) query2.getSingleResult();
            System.out.println(avgGrade);
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
