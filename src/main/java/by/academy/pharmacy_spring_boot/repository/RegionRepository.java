package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
