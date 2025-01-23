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
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Integer bookId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "uid", referencedColumnName = "uid")
//    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "pages")
    private Integer pages;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category1_id", referencedColumnName = "category_id")
    private Category category1;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category2_id", referencedColumnName = "category_id")
    private Category category2;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category3_id", referencedColumnName = "category_id")
    private Category category3;

    @Column(name = "cover_link")
    private String cover_link;

//    @ManyToMany(mappedBy = "readerBooks", fetch = FetchType.LAZY)
//    private Set<User> users = new HashSet<>();
//
    @JsonBackReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ListElement> listElements = new HashSet<>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Review> reviewsSet = new HashSet<>();

    public Book() {
    }

    public Book(String title, String description, Integer pages, String cover_link/*, User user*/, Category category1, Category category2, Category category3) {
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.cover_link = cover_link;
        //this.user = user;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
    }
}

