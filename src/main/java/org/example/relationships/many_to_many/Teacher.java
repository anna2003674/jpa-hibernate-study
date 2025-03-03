package org.example.relationships.many_to_many;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "subject")
    private String subject;

    @Column(name = "is_professor")
    private boolean isProfessor;

    public Teacher() {
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_uni",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "university_id"))
    private List<University> universities = new ArrayList<>();

    public void addUniversityToTeacher(University university) {
        universities.add(university);
    }

    public Teacher(String name, String surname, String subject, boolean isProfessor) {
        this.name = name;
        this.surname = surname;
        this.subject = subject;
        this.isProfessor = isProfessor;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isProfessor() {
        return isProfessor;
    }

    public void setProfessor(boolean professor) {
        isProfessor = professor;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", subject='" + subject + '\'' +
            ", isProfessor=" + isProfessor +
            '}';
    }
}
