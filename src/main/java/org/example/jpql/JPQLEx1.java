package org.example.jpql;

import jakarta.persistence.*;
import org.example.jpql.entity.Student;

import java.util.List;

public class JPQLEx1 {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("select s from Student s");
            List<Student> students = query.getResultList();
            System.out.println(students);

            TypedQuery<Student> query1 = entityManager.createQuery("select s from Student s", Student.class);
            List<Student> stud1 = query1.getResultList();
            System.out.println(stud1);

            List<Student> stud2 = entityManager.createQuery("select s from Student s" +
                " where s.name = 'Leo'").getResultList();
            System.out.println(stud2);

            List<Student> stud3 = entityManager.createQuery("select s from Student s" +
                " where avgGrade>8.5").getResultList();
            System.out.println(stud3);

            List<Student> stud4 = entityManager.createQuery("select s from Student s" +
                " where avgGrade between 7 and 8").getResultList();
            System.out.println(stud4);
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
