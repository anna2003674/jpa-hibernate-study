package org.example.n_plus_one;

import jakarta.persistence.*;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();
        try {
            // Запрос, вызывающий проблему N+1
            List<Book> books = entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();

            for (Book book : books) {
                System.out.println(book.getAuthor().getName()); // Hibernate делает отдельный запрос для каждого автора!
            }

            // Решение проблемы (Один запрос вместо N+1)
            List<Book> books1 = entityManager.createQuery(
                    "SELECT b FROM Book b JOIN FETCH b.author", Book.class)
                .getResultList();

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
