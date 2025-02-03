package com.shelfService.shelfSyncBE.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Reader extends User {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_books",
            joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> readerBooks = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ListElement> bookProgress = new HashSet<>();

    public void addBook(Book book) {
        this.readerBooks.add(book);
        book.getReaders().add(this);
    }
}
