package by.academy.pharmacy_spring_boot.specifications;

import by.academy.pharmacy_spring_boot.entity.Category;
import by.academy.pharmacy_spring_boot.entity.Category_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CategorySpecification {

    public static Specification<Category> getCategoryByTitle(String category) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (category != null) {
                predicateList.add(criteriaBuilder.like(root.get(Category_.TITLE), "%" + category + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
