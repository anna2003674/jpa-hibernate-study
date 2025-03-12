package org.example.advanced_mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.advanced_mapping.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class ListMappingStringEx {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<String> friendsNames = new ArrayList<>();
            friendsNames.add("Chanel");
            friendsNames.add("Leo");
            friendsNames.add("Julia");
            Employee employee1 = new Employee("Michael", 4000, 15d, friendsNames);
            entityManager.persist(employee1);
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
