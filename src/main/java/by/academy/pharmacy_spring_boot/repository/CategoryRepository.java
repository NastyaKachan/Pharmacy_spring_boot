package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.Category;
import by.academy.pharmacy_spring_boot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    @Query("SELECT c.products FROM Category c where c.id = :categoryId")
    List<Product> findProductOfCategory(@Param("categoryId") Integer categoryId);

}
