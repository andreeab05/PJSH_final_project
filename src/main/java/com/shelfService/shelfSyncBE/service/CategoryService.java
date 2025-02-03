package com.shelfService.shelfSyncBE.service;

import com.shelfService.shelfSyncBE.entity.Category;
import com.shelfService.shelfSyncBE.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Category getCategoryById(Integer categoryId) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new Exception("{getCategoryId} - Cannot find category");
        }
        return optionalCategory.get();
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId){
        categoryRepository.deleteById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
