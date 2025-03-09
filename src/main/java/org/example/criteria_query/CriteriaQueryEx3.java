package org.example.criteria_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.criteria_query.entity.Student;

import java.util.List;

public class CriteriaQueryEx3 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
            Root<Student> root = criteriaQuery.from(Student.class);
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("avgGrade"), 7.5);
            criteriaQuery.where(predicate);
            criteriaQuery.select(root);
            TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);
            List<Student> names = query.getResultList();
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
