package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.MNN;
import by.academy.pharmacy_spring_boot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MnnRepository extends JpaRepository<MNN, Integer>, JpaSpecificationExecutor<MNN> {

    @Query("SELECT c.products FROM MNN c where c.id = :mnnId")
    List<Product> findProductsWithMnn(@Param("mnnId") Integer mnnId);
}
