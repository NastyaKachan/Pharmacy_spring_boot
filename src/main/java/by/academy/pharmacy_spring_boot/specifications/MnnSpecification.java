package by.academy.pharmacy_spring_boot.specifications;

import by.academy.pharmacy_spring_boot.entity.MNN;
import by.academy.pharmacy_spring_boot.entity.MNN_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MnnSpecification {

    public static Specification<MNN> getMnnByTitle(String mnn) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (mnn != null) {
                predicateList.add(criteriaBuilder.like(root.get(MNN_.MNN), "%" + mnn + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
