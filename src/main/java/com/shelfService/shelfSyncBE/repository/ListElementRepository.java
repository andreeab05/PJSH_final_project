package com.shelfService.shelfSyncBE.repository;
import com.shelfService.shelfSyncBE.entity.ListElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListElementRepository extends JpaRepository<ListElement, Integer> {
//    ListElement findByUserAndBook(User user, Book book);
}
