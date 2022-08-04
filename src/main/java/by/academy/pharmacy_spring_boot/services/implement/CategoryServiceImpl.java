package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.CategoryDto;
import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.entity.Category;
import by.academy.pharmacy_spring_boot.filters.CategoryFilter;
import by.academy.pharmacy_spring_boot.mapper.CategoryMapper;
import by.academy.pharmacy_spring_boot.mapper.ProductMapper;
import by.academy.pharmacy_spring_boot.repository.CategoryRepository;
import by.academy.pharmacy_spring_boot.services.interfaces.CategoryService;
import by.academy.pharmacy_spring_boot.specifications.CategorySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryDto> findCategoryWithPaginated(CategoryFilter categoryFilter, int numberPage, int size, String sortField,
                                                       String sortDir) {
        Specification<Category> categorySpecification =
                Specification
                        .where(Optional.ofNullable(categoryFilter.getCategoryFilter())
                                .map(CategorySpecification::getCategoryByTitle)
                                .orElse(null));
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable page = PageRequest.of(numberPage - 1, size, sort);
        return categoryRepository.findAll(categorySpecification, page).map(categoryMapper::toDto);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findCategoryById(Integer id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public List<ProductDto> findProductOfCategory(Integer categoryId) {
        return categoryRepository.findProductOfCategory(categoryId)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toEntity(categoryDto));
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
