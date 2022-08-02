package by.academy.pharmacy_spring_boot.specifications;

import by.academy.pharmacy_spring_boot.entity.PharmacyChain;
import by.academy.pharmacy_spring_boot.entity.PharmacyChain_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PharmacyChainSpecification {

    public static Specification<PharmacyChain> getChainByName(String nameChain) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (nameChain != null) {
                predicatesMain.add(criteriaBuilder.like(root.get(PharmacyChain_.NAME_CHAIN), "%" + nameChain + "%"));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[0]));
        };
    }

}
