package ru.bazhanov.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tools")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;
    private String name;
    private String specifications;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id",nullable = false)
    private Address address;

    private Double price;

    @OneToMany(mappedBy = "tool")
    private List<Order> orders;


    Tool(){
    }
    public Tool(String name, String specifications, Person person, Address address,double price){
        this.name = name;
        this.specifications = specifications;
        this.person = person;
        this.address = address;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }
    public String getPersonName(){
        return this.person.getName();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDistrict(){
        return this.address.getDistrict();
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tool tool = (Tool) o;
        return id == tool.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
