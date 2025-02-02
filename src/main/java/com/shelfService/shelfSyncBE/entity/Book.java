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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private Author author;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "pages")
    private Integer pages;

    @JsonBackReference(value = "book-category1")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category1_id", referencedColumnName = "category_id")
    private Category category1;

    @JsonBackReference(value = "book-category2")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category2_id", referencedColumnName = "category_id")
    private Category category2;

    @JsonBackReference(value = "book-category3")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category3_id", referencedColumnName = "category_id")
    private Category category3;

    @Column(name = "cover_link")
    private String cover_link;

    @ManyToMany(mappedBy = "readerBooks", fetch = FetchType.EAGER)
    private Set<Reader> readers = new HashSet<>();

    @JsonManagedReference(value = "book-listelement")
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ListElement> listElements = new HashSet<>();

    @JsonManagedReference(value = "book-reviews")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Review> reviewsSet = new HashSet<>();

    public Book() {
    }

    public void addReader(Reader reader){
        readers.add(reader);
    }

    public Book(String title, String description, Integer pages, String cover_link, Author author, Category category1, Category category2, Category category3) {
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.cover_link = cover_link;
        this.author = author;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
    }
}

