package ru.bazhanov.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "tool_id")
    private Tool tool;


    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;


    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "stop_date")
    private LocalDate stopDate;

    private Boolean stopped;
    private Boolean completed;

    public Order(Person person, Tool tool, LocalDate startDate, LocalDate stopDate){
        this.person = person;
        this.tool = tool;
        this.startDate = startDate;
        this.stopDate = stopDate;
        stopped = false;
        completed = false;
    }
    public Order(){

    }

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }
    public String getToolName(){
        return tool.getName();
    }

    public Tool getTool() {
        return tool;
    }

    public  Double getToolPrice(){
        return tool.getPrice();
    }

    public Boolean getStopped() {
        return stopped;
    }

    public void setStopped(Boolean stopped) {
        this.stopped = stopped;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    public String getPersonName(){
        return this.person.getName();
    }
}
