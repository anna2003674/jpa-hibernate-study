package org.example.advanced_mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.advanced_mapping.entity.Address;
import org.example.advanced_mapping.entity.Employee;

public class CompositeTypeMappingEx {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Address address = new Address("USA", "Chicago", "Dempster", 40);
            Employee employee = new Employee("Michael", 4000, 15d, address);
            entityManager.persist(employee);
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
