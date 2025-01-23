package com.shelfService.shelfSyncBE.controller;

import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books") // Endpoint-ul pentru cărți
public class BookController {

    @Autowired
    private BookService bookService;

//    // Endpoint pentru a obține toate cărțile
//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBooks() {
//        List<Book> books = bookService.getAllBooks();
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }

    // Endpoint pentru a obține o carte după ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer bookId) {
//        try {
//            Book book = bookService.getBookById(bookId);
//            return new ResponseEntity<>(book, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Returnează 404 dacă nu găsește cartea
//        }
//    }

    // Endpoint pentru a crea o carte
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            bookService.createBook(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED); // Returnează 201 la succes
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Returnează 400 în caz de eroare
        }
    }

    // Endpoint pentru a actualiza o carte după ID
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer bookId, @RequestBody Book book) {
        try {
            // Setăm ID-ul cărții pentru a actualiza corect entitatea
            book.setBookId(bookId);
            bookService.updateBookById(book);
            return new ResponseEntity<>(book, HttpStatus.OK); // Returnează 200 la succes
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Returnează 404 dacă nu găsește cartea
        }
    }

    // Endpoint pentru a șterge o carte după ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer bookId) {
        try {
            bookService.deleteBookById(bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returnează 204 la succes
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returnează 404 dacă nu găsește cartea
        }
    }
}
