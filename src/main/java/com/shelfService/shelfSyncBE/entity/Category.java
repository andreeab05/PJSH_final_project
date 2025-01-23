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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "category1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Book> booksSet1 = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "category2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Book> booksSet2 = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "category3", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Book> booksSet3 = new HashSet<>();

    public Category() {
    }

}

