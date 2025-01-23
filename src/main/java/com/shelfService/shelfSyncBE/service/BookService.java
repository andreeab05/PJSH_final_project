package com.shelfService.shelfSyncBE.service;

import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.entity.Category;
import com.shelfService.shelfSyncBE.repository.BookRepository;
import com.shelfService.shelfSyncBE.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

//    public Book getBookByBookIdAndReaderId(String uid, Integer bookId) {
//    }
//
//    public Book getBookByBookIdAuthor(Integer bookId) {
//    }
//
//    public List<Book> getAllBooksByAuthorId(String uid) {
//    }
//
//    public List<Book> getAllBooksByReaderId(String uid) {
//    }

    public void createBook(Book book) throws Exception{
        //User user = userRepository.findByUid(addBookDTO.getUid());

        // Retrieve categories
        Optional<Category> optionalCategory1 = categoryRepository.findById(book.getCategory1().getCategoryId());
        Optional<Category> optionalCategory2 = categoryRepository.findById(book.getCategory2().getCategoryId());
        Optional<Category> optionalCategory3 = categoryRepository.findById(book.getCategory3().getCategoryId());

//         Check if all required categories exist
        if (optionalCategory1.isEmpty() || optionalCategory2.isEmpty() || optionalCategory3.isEmpty()) {
            throw new Exception("{createBook} - Cannot find categories");
        }

//        // Validate user
//        if (user == null) {
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//        }

        bookRepository.save(book);
    }

    public void updateBookById(Book book) throws Exception{
        Optional<Book> optionalBook = bookRepository.findById(book.getBookId());

        if (optionalBook.isEmpty())
            throw new Exception("{updateBookById} - Book not found");

        // Retrieve categories
        Optional<Category> optionalCategory1 = categoryRepository.findById(book.getCategory1().getCategoryId());
        Optional<Category> optionalCategory2 = categoryRepository.findById(book.getCategory2().getCategoryId());
        Optional<Category> optionalCategory3 = categoryRepository.findById(book.getCategory3().getCategoryId());

        // Check if all required categories exist
        if (optionalCategory1.isEmpty() || optionalCategory2.isEmpty() || optionalCategory3.isEmpty()) {
            throw new Exception("{updateBookById} - Categories not found");
        }

        bookRepository.save(book);
    }

    public void deleteBookById(Integer bookId) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty())
            throw new Exception("Book not found");
        bookRepository.deleteById(bookId);
    }
}

