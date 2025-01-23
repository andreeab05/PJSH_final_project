package com.shelfService.shelfSyncBE.controller;

import com.shelfService.shelfSyncBE.entity.Category;
import com.shelfService.shelfSyncBE.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories") // Endpoint-ul pentru categorii
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Endpoint pentru a obține toate categoriile
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // Endpoint pentru a obține o categorie după ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer categoryId) {
        try {
            Category category = categoryService.getCategoryById(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Returnează 404 dacă nu găsește categoria
        }
    }

    // Endpoint pentru a crea o categorie
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            categoryService.createCategory(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED); // Returnează 201 la succes
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Returnează 400 în caz de eroare
        }
    }

    // Endpoint pentru a șterge o categorie după ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returnează 204 la succes
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returnează 404 dacă nu găsește categoria
        }
    }
}
