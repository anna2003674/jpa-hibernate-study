package org.example.advanced_mapping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private Integer house;

    public Address() {
    }

    public Address(String country, String city, String street, Integer house) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Address{" +
            "country='" + country + '\'' +
            ", city='" + city + '\'' +
            ", street='" + street + '\'' +
            ", house=" + house +
            '}';
    }
}