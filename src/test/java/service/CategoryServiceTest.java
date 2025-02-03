package service;

import com.shelfService.shelfSyncBE.entity.Category;
import com.shelfService.shelfSyncBE.repository.CategoryRepository;
import com.shelfService.shelfSyncBE.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category mockCategory;

    @BeforeEach
    public void init(){
        mockCategory = new Category();
        mockCategory.setCategoryId(1);
        mockCategory.setName("horror");
    }

    @Test
    void testGetCategoryByUid() throws Exception {
        when(categoryRepository.findById(1)).thenReturn(Optional.ofNullable(mockCategory));

        Category category = categoryService.getCategoryById(1);

        assertNotNull(category);
        assertEquals("horror", category.getName());

        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setName("fantasy");
        category.setCategoryId(2);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category newCategory = categoryService.createCategory(category);

        assertNotNull(newCategory);
        assertEquals("fantasy", newCategory.getName());

        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testGetAllCategories() {
        Category category = new Category();
        category.setName("fantasy");
        Category category2 = new Category();
        category2.setName("horror");
        Category category3 = new Category();
        category3.setName("romance");
        Category category4 = new Category();
        category4.setName("psychology");

        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(category);
        mockCategories.add(category2);
        mockCategories.add(category3);
        mockCategories.add(category4);

        when(categoryRepository.findAll()).thenReturn(mockCategories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(4, result.size());
        assertEquals("fantasy", result.get(0).getName());
        assertEquals("horror", result.get(1).getName());
        assertEquals("romance", result.get(2).getName());
        assertEquals("psychology", result.get(3).getName());

        verify(categoryRepository, times(1)).findAll();
    }

}
