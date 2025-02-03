package com.shelfService.shelfSyncBE.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonManagedReference(value = "book-category1")
    @OneToMany(mappedBy = "category1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Book> booksSet1 = new HashSet<>();

    @JsonManagedReference(value = "book-category2")
    @OneToMany(mappedBy = "category2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Book> booksSet2 = new HashSet<>();

    @JsonManagedReference(value = "book-category3")
    @OneToMany(mappedBy = "category3", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Book> booksSet3 = new HashSet<>();

    public Category() {
    }

    public Category(Integer categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
}

