package service;

import com.shelfService.shelfSyncBE.entity.Category;
import com.shelfService.shelfSyncBE.repository.CategoryRepository;
import com.shelfService.shelfSyncBE.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void testAddCategory() {
        Category category1 = new Category();
        category1.setName("fantasy");
        categoryService.createCategory(category1);

        Category category2 = new Category();
        category2.setName("horror");
        categoryService.createCategory(category2);

        Category category3 = new Category();
        category3.setName("drama");
        categoryService.createCategory(category3);

        List<Category> result = categoryRepository.findAll();
        Assertions.assertEquals(result.size(), 3);
        Assertions.assertEquals(result.get(0).getName(), "fantasy");
        Assertions.assertEquals(result.get(1).getName(), "horror");
        Assertions.assertEquals(result.get(2).getName(), "drama");
    }

    @Test
    void testDeleteCategory() {
        Category category1 = new Category();
        category1.setName("fantasy");
        categoryService.createCategory(category1);

        categoryService.deleteCategory(category1.getCategoryId());

        Optional<Category> result = categoryRepository.findById(category1.getCategoryId());
        Assertions.assertEquals(result.get(), null);
    }
}
