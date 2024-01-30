package dev.cyan.june2023javaspring.repository;

import dev.cyan.june2023javaspring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<List<Car>> findCarsByPower(int value);
    Optional<List<Car>> findCarsByProducer(String value);
}
