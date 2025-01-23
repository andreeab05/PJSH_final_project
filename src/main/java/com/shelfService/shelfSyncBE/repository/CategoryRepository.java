package com.shelfService.shelfSyncBE.repository;

import com.shelfService.shelfSyncBE.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

