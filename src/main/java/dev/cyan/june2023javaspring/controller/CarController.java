package dev.cyan.june2023javaspring.controller;

import dev.cyan.june2023javaspring.entity.Car;
import dev.cyan.june2023javaspring.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable int id) {
        Optional<Car> byId = carRepository.findById(id);
        return byId.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car) {
        return new ResponseEntity<>(carRepository.save(car), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        carRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/power/{value}")
    public ResponseEntity<List<Car>> getByPower(@PathVariable int value) {
        Optional<List<Car>> byPower = carRepository.findCarsByPower(value);
        return byPower.map(cars -> new ResponseEntity<>(cars, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/producer/{value}")
    public ResponseEntity<List<Car>> getByProducer(@PathVariable String value) {
        Optional<List<Car>> byProducer = carRepository.findCarsByProducer(value);
        return byProducer.map(cars -> new ResponseEntity<>(cars, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
