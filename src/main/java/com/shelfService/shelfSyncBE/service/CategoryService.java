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
        if (optionalCategory.isPresent()) {
            throw new Exception("{getCategoryId} - Cannot find categgory");
        }
        return optionalCategory.get();
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId){
        categoryRepository.deleteById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
