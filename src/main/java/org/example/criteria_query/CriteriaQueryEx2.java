package org.example.criteria_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.criteria_query.entity.Student;

import java.util.List;

public class CriteriaQueryEx2 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
            Root<Student> root = criteriaQuery.from(Student.class);
            criteriaQuery.select(root.get("name"));
            TypedQuery<String> query = entityManager.createQuery(criteriaQuery);
            List<String> names = query.getResultList();
            System.out.println(names);
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
