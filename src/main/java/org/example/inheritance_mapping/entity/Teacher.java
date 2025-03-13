package org.example.inheritance_mapping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="teachers")
public class Teacher extends Employee {
    @Column(name = "subject")
    private String subject;

    @Column(name = "is_professor")
    private Boolean isProfessor;

    public Teacher() {
    }

    public Teacher(String name, Integer salary, Double experience, String subject, Boolean isProfessor) {
        super(name, salary, experience);
        this.subject = subject;
        this.isProfessor = isProfessor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getProfessor() {
        return isProfessor;
    }

    public void setProfessor(Boolean professor) {
        isProfessor = professor;
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "id='" + getId() + '\'' +
            "name='" + getName() + '\'' +
            "salary='" + getSalary() + '\'' +
            "experience='" + getExperience() + '\'' +
            "subject='" + subject + '\'' +
            ", isProfessor=" + isProfessor +
            '}';
    }
}
