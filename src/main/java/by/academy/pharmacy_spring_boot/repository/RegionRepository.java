package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.City;
import by.academy.pharmacy_spring_boot.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Query("SELECT c.cities FROM Region c where c.id = :regionId")
    List<City> findCitiesOfRegion(@Param("regionId") Integer regionId);
}
