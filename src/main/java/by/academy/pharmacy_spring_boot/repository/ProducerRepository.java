package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer>, JpaSpecificationExecutor<Producer> {
}
