package org.example.inheritance_mapping.entity;

import jakarta.persistence.*;

//@Entity
@Table(name = "employees")
//@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public abstract class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "experience")
    private Double experience;

    public Employee() {
    }

    public Employee(String name, Integer salary, Double experience) {
        this.name = name;
        this.salary = salary;
        this.experience = experience;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }
}
