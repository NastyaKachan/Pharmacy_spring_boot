package by.academy.pharmacy_spring_boot.services.implement;

import by.academy.pharmacy_spring_boot.dto.CategoryDto;
import by.academy.pharmacy_spring_boot.entity.Category;
import by.academy.pharmacy_spring_boot.filters.CategoryFilter;
import by.academy.pharmacy_spring_boot.mapper.CategoryMapper;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
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
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    @Override
    public void createCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toEntity(categoryDto));
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toEntity(categoryDto));
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
