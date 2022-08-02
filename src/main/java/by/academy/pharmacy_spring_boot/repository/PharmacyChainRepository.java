package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.PharmacyChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyChainRepository extends JpaRepository<PharmacyChain, Integer>, JpaSpecificationExecutor<PharmacyChain> {
}
