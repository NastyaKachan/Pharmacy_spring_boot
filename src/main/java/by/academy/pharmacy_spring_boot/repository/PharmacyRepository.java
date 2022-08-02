package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer>, JpaSpecificationExecutor<Pharmacy> {
}
