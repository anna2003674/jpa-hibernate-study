package org.example.criteria_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.example.criteria_query.entity.Student;
import org.example.criteria_query.entity.University;

import java.util.List;

public class CriteriaQueryEx5 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<University> root = criteriaQuery.from(University.class);
            Join<University, Student> join = root.join("students");
            criteriaQuery.multiselect(root, join);
            TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
            List<Object[]> students = query.getResultList();
            for (Object[] info : students) {
                System.out.println(info[0] + " " + info[1]);
            }
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
