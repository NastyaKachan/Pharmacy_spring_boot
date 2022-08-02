package by.academy.pharmacy_spring_boot.repository;

import by.academy.pharmacy_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String userName);
}
