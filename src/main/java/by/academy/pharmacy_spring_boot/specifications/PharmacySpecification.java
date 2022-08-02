package by.academy.pharmacy_spring_boot.specifications;

import by.academy.pharmacy_spring_boot.entity.Pharmacy;
import by.academy.pharmacy_spring_boot.entity.Pharmacy_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PharmacySpecification {

    public static Specification<Pharmacy> getPharmacyByTitle(String pharmacy) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (pharmacy != null) {
                predicateList.add(criteriaBuilder.like(root.get(Pharmacy_.NAME_PHARMACY), "%" + pharmacy + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
