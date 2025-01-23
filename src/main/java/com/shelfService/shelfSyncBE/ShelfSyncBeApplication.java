package com.shelfService.shelfSyncBE;

import com.shelfService.shelfSyncBE.entity.Category;
import com.shelfService.shelfSyncBE.repository.CategoryRepository;
import com.shelfService.shelfSyncBE.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShelfSyncBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShelfSyncBeApplication.class, args);
	}

}
