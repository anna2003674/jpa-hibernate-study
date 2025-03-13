package org.example.inheritance_mapping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="drivers")
public class Driver extends Employee {
    @Column(name = "category")
    private Character category;

    @Column(name = "car_brand")
    private String carBrand;

    public Driver() {
    }

    public Driver(String name, Integer salary, Double experience, Character category, String carBrand) {
        super(name, salary, experience);
        this.category = category;
        this.carBrand = carBrand;
    }

    public Character getCategory() {
        return category;
    }

    public void setCategory(Character category) {
        this.category = category;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public String toString() {
        return "Driver{" +
            "id='" + getId() + '\'' +
            "name='" + getName() + '\'' +
            "salary='" + getSalary() + '\'' +
            "experience='" + getExperience() + '\'' +
            "category='" + category + '\'' +
            ", carBrand=" + carBrand +
            '}';
    }
}
