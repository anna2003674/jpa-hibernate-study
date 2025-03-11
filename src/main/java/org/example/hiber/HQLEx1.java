package org.example.hiber;

import org.example.hiber.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HQLEx1 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            // select * from students
            Query<Student> query = session.createQuery("from Student");
            List<Student> students = query.getResultList();
            for (Student s : students) {
                System.out.println(s);
            }
            System.out.println("-----------");
            List<Student> stud1 = session
                .createQuery("from Student where lower(name) like '%l%' and avgGrade > 8")
                .getResultList();
            for (Student s : stud1) {
                System.out.println(s);
            }
            System.out.println("-----------");
            session.createQuery("update Student set avgGrade=10.0 where length(name)=5").executeUpdate();
            System.out.println("-----------");
            session.createQuery("delete Student where avgGrade < 9").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            factory.close();
        }
    }
}
