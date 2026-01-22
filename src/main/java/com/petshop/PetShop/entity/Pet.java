package com.petshop.PetShop.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String species;

    @Column(length = 100)
    private String breed;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    protected Pet() {}

    public Pet(User owner, String name, String species) {
        this.owner = owner;
        this.name = name;
        this.species = species;
    }

    public Long getId() {
        return id;
    }
    public User getOwner() {
        return owner;
    }
    public String getName() {
        return name;
    }
    public String getSpecies() {
        return species;
    }
    public String getBreed() {
        return breed;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
}
