package com.shelfService.shelfSyncBE.repository;
import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.entity.ListElement;
import com.shelfService.shelfSyncBE.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListElementRepository extends JpaRepository<ListElement, Integer> {
    ListElement findByUserAndBook(Reader user, Book book);
}
