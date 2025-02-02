package com.shelfService.shelfSyncBE.controller;

import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Endpoint pentru a obține o carte după ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer bookId) {
        try {
            Book book = bookService.getBookById(bookId);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pentru a crea o carte
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestParam Integer uid, @RequestBody Book book) {
        try {
            bookService.createBook(uid, book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint pentru a actualiza o carte după ID
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer bookId, @RequestBody Book book) {
        try {
            // Setăm ID-ul cărții pentru a actualiza corect entitatea
            book.setBookId(bookId);
            bookService.updateBookById(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pentru a adauga un reader la o carte
    @PutMapping("/addReader/{id}")
    public ResponseEntity<Book> addReader(@PathVariable("id") Integer bookId, @RequestParam Integer uid) {
        try {
            Book book = bookService.addReader(bookId, uid);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("eroarea e" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pentru a șterge o carte după ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer bookId) {
        try {
            bookService.deleteBookById(bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
