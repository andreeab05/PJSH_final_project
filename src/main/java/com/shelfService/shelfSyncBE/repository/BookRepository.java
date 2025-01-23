package com.shelfService.shelfSyncBE.repository;

import com.shelfService.shelfSyncBE.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
