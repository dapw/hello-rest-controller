package com.galvanize.hellorestcontroller.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {

    private String name;
    @JsonFormat(pattern = "M/d/yyyy")
    private LocalDate dateRegistered;
    private String email;
    private String address;

    public Person() {
    }
    public Person(String name, LocalDate dateRegistered, String email, String address) {

        this.name = name;
        this.dateRegistered = dateRegistered;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }
    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public long getYearsRegistered() {

        long years = ChronoUnit.YEARS.between(dateRegistered, LocalDate.now());

        return years;
    }

    @Override
    public String toString() {

        return "Person [name=" + name + ", dateRegistered=" + dateRegistered + ", email=" + email + ", address=" + address + "]";
    }
}