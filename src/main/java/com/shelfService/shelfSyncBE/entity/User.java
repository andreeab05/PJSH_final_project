package com.shelfService.shelfSyncBE.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "description")
    private String description;

    @Column(name = "username")
    private String username;

    @JsonManagedReference("user-feedbacks")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Feedback> feedbackSet = new HashSet<>();

    @JsonManagedReference(value = "user-review")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviewsSet = new HashSet<>();

}
