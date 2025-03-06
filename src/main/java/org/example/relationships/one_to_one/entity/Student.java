package org.example.relationships.one_to_one.entity;

import jakarta.persistence.*;

//@Entity
//@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "avg_grade")
    private Double avg_grade;

    @OneToOne
    @JoinColumn(name = "passport_id")
    private Passport passport;

    public Student() {
    }

    public Student(String name, String surname, Double avg_grade) {
        this.name = name;
        this.surname = surname;
        this.avg_grade = avg_grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getAvg_grade() {
        return avg_grade;
    }

    public void setAvg_grade(Double avg_grade) {
        this.avg_grade = avg_grade;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", avg_grade='" + avg_grade + '\'' +
            '}';
    }
}
