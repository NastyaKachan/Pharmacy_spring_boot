package by.academy.pharmacy_spring_boot.specifications;

import by.academy.pharmacy_spring_boot.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> getProductByName(String drugName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (drugName != null) {
                predicateList.add(criteriaBuilder.like(root.get(Product_.DRUG_NAME), "%" + drugName + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }

    public static Specification<Product> getProductByProducer(String producer) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (producer != null) {
                Join<Product, Producer> producerJoin = root.join(Product_.producer);
                predicateList.add(criteriaBuilder.like(producerJoin.get(Producer_.name_producer.getName()), "%" + producer + "%"));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }

    public static Specification<Product> getProductByPharmacy(String pharmacy) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (pharmacy != null) {
                query.distinct(true);
                ListJoin<Product, Pharmacy> pharmacyListJoin = root.join(Product_.pharmacies);
                predicateList.add(criteriaBuilder.like(pharmacyListJoin.get(Pharmacy_.namePharmacy.getName()), "%" + pharmacy + "%"));

            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }

}
