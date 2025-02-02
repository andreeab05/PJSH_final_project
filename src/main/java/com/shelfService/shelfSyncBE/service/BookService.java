package com.shelfService.shelfSyncBE.service;

import com.shelfService.shelfSyncBE.entity.*;
import com.shelfService.shelfSyncBE.repository.BookRepository;
import com.shelfService.shelfSyncBE.repository.CategoryRepository;
import com.shelfService.shelfSyncBE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

//    public List<Book> getAllBooksByAuthorId(String uid) {
//    }
//
//    public List<Book> getAllBooksByReaderId(String uid) {
//    }

    public Book addReader(Integer bookId, Integer readerId) throws Exception {
        Reader reader = (Reader) userRepository.findByUid(readerId);
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        System.out.println("a gasit si cartea si readerul");
        if (optionalBook.get() != null) {
            Book book = optionalBook.get();
            book.addReader(reader);
            reader.addBook(book);
            bookRepository.save(book);
            userRepository.save(reader);
        } else throw new Exception("{addReader} - Book doesn't exist");
        return null;
    }

    public void createBook(Integer userId, Book book) throws Exception {
        User user = userRepository.findByUid(userId);
        if (user == null) {
            throw new Exception("{createBook} - Cannot find user");
        }

        if (user instanceof Author)
            book.setAuthor((Author) user);

        // Retrieve categories
        Optional<Category> optionalCategory1 = categoryRepository.findById(book.getCategory1().getCategoryId());
        Optional<Category> optionalCategory2 = categoryRepository.findById(book.getCategory2().getCategoryId());
        Optional<Category> optionalCategory3 = categoryRepository.findById(book.getCategory3().getCategoryId());

//         Check if all required categories exist
        if (optionalCategory1.isEmpty() || optionalCategory2.isEmpty() || optionalCategory3.isEmpty()) {
            throw new Exception("{createBook} - Cannot find categories");
        }

        bookRepository.save(book);
    }

    public void updateBookById(Book book) throws Exception {
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

    public Book getBookById(Integer bookId){
        return bookRepository.findById(bookId).get();
    }
}

