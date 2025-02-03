package com.shelfService.shelfSyncBE.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Integer reviewId;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "comment")
    private String comment;

    @JsonBackReference(value = "book-reviews")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @JsonBackReference(value = "user-review")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User user;

    public Review() {
    }

    public Review(Integer rating, String comment, User user, Book book) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.book = book;
        //this.date_modified = LocalDate.now();
    }

}

