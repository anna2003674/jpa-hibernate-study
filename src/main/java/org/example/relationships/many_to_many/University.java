package org.example.relationships.many_to_many;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "universities")
public class University {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "founding_date")
    private Date foundingDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_uni",
        joinColumns = @JoinColumn(name = "university_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers = new ArrayList<>();

    public void addTeacherToUniversity(Teacher teacher) {
        teachers.add(teacher);
    }

    public University() {
    }

    public University(String name, Date foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
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

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "University{" +
            "id=" + id +
            ", name=" + name +
            ", foundingDate=" + foundingDate +
            '}';
    }
}
