package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.Pharmacy;
import by.academy.pharmacy_spring_boot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer>,
        JpaSpecificationExecutor<Pharmacy> {

    @Query("SELECT c.products FROM Pharmacy c where c.id = :pharmacyId")
    List<Product> findProductsOfPharmacy(@Param("pharmacyId") Integer pharmacyId);
}
