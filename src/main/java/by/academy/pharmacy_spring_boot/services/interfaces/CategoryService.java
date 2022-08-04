package by.academy.pharmacy_spring_boot.services.interfaces;

import by.academy.pharmacy_spring_boot.dto.CategoryDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.filters.CategoryFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<CategoryDto> findCategoryWithPaginated(CategoryFilter categoryFilter, int numberPage, int size, String sortField,
                                                String sortDir);

    List<CategoryDto> findAllCategories();

    CategoryDto findCategoryById(Integer id);

    List<ProductDto> findProductOfCategory(Integer categoryId);

    void saveCategory(CategoryDto categoryDto);

    void deleteCategoryById(Integer id);
}
