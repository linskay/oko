package com.upravdom.oko.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String name;
    @ManyToOne
    private City city;

    public Trainee(String lastName, String name, City city) {
        this.lastName = lastName;
        this.name = name;
        this.city = city;
    }

    public Trainee() {
    }
}