package by.academy.pharmacy_spring_boot.specifications;

import by.academy.pharmacy_spring_boot.entity.Producer;
import by.academy.pharmacy_spring_boot.entity.Producer_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProducerSpecification {

    public static Specification<Producer> getProducerByName(String nameProducer) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (nameProducer != null) {
                predicateList.add(criteriaBuilder.like(root.get(Producer_.NAME_PRODUCER), "%" + nameProducer + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
