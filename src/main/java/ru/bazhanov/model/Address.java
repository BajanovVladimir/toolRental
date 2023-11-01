package ru.bazhanov.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;
    private String district;
    private String street;


    Address(){

    }
    public Address(String district,String street){
        this.district = district;
        this.street = street;
    }
    public Address(int id,String district,String street){
        this.id = id;
        this.district = district;
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getId() {
        return id;
    }
}
