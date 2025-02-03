package com.shelfService.shelfSyncBE.aspects;

import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.events.books.AddReaderEvent;
import com.shelfService.shelfSyncBE.events.books.CreateBookEvent;
import com.shelfService.shelfSyncBE.events.books.DeleteBookEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class BookAspect {
    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("execution(* com.shelfService.shelfSyncBE.service.BookService.createBook(..))")
    public void anyCreateBook() {}

    @Pointcut("execution(* com.shelfService.shelfSyncBE.service.BookService.deleteBookById(..))")
    public void anyDeleteBook() {}

    @Pointcut("execution(* com.shelfService.shelfSyncBE.service.BookService.addReader(..))")
    public void anyAddReader() {}
    @Before("anyCreateBook()")
    public void beforeCreateBook(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer userId = (Integer) args[0];
        Book book = (Book) args[1];

        applicationContext.publishEvent(new CreateBookEvent(this, userId, book));
    }
    @After("anyDeleteBook()")
    public void afterSuccessfulDeleteBook(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer bookId = (Integer) args[0];

        applicationContext.publishEvent(new DeleteBookEvent(this, bookId));
    }

    @After("anyAddReader()")
    public void afterSuccessfulAddReader(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer bookId = (Integer) args[0];
        Integer readerId = (Integer) args[1];

        applicationContext.publishEvent(new AddReaderEvent(this, bookId, readerId));
    }
}
