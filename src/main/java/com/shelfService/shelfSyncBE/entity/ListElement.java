package com.shelfService.shelfSyncBE.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "list_elements")
public class ListElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "element_id")
    private Integer elementId;

    @Column(name = "progress")
    private String progress;

    @Column(name = "current_pages")
    private Integer current_pages;

    @JsonBackReference(value = "book-listelement")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User user;

    public ListElement() {
    }

    public ListElement(User user, Book book, String progress, Integer current_pages) {
        this.user = user;
        this.book = book;
        this.progress = progress;
        this.current_pages = current_pages;
    }
}
