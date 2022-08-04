package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.Pharmacy;
import by.academy.pharmacy_spring_boot.entity.PharmacyChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyChainRepository extends JpaRepository<PharmacyChain, Integer>,
        JpaSpecificationExecutor<PharmacyChain> {

    @Query("SELECT c.pharmacies FROM PharmacyChain c where c.id = :chainId")
    List<Pharmacy> findPharmacyOfChain(@Param("chainId") Integer chainId);
}
