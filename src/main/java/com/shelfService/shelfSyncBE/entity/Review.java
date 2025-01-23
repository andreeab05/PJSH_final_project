package com.shelfService.shelfSyncBE.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
//    @Temporal(TemporalType.DATE)
//    @Column(name = "date_modified")
//    private LocalDate date_modified;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "uid", referencedColumnName = "uid")
//    private User user;

    public Review() {
    }

    public Review(Integer rating, String comment,/* User user,*/ Book book) {
        this.rating = rating;
        this.comment = comment;
        //this.user = user;
        this.book = book;
        //this.date_modified = LocalDate.now();
    }

}

