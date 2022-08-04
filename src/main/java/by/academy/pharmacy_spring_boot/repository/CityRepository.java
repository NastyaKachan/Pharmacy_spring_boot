package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.City;
import by.academy.pharmacy_spring_boot.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT c.pharmacies FROM City c where c.id = :cityId")
    List<Pharmacy> findPharmacyOfCity(@Param("cityId") Integer cityId);

}
