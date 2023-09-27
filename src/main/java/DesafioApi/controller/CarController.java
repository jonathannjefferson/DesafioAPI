package DesafioApi.controller;


import DesafioApi.Repository.CarRepository;
import DesafioApi.domain.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/cars")
public class CarController {

    private CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        super();
        this.carRepository = carRepository;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Car>> deleteCarByUserLogged(@PathVariable Long id) {
        try {
            carRepository.deleteById(id);
            return new ResponseEntity<Optional<Car>>(HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<Optional<Car>>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCarsByUserLogged() {
        List<Car> allCarsFromUserLogged = new ArrayList<>();
        allCarsFromUserLogged = carRepository.findAll();
        return new ResponseEntity<>(allCarsFromUserLogged, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Car>> getCarByUserLogged(Long id) {
        Optional<Car> car;

        try {
            car = carRepository.findById(id);
            return new ResponseEntity<Optional<Car>>(car, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<Optional<Car>>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity<Car> registerNewCarForLoggedUser(@RequestBody Car car) {
        Car save = carRepository.save(car);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Car> updateCarByUserLogged(@PathVariable Long id, @RequestBody Car updateCar) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setYear(updateCar.getYear());
                    car.setLicensePlate(updateCar.getLicensePlate());
                    car.setModel(updateCar.getModel());
                    car.setColor(updateCar.getColor());

                    Car carUpdated = carRepository.save(car);
                    return ResponseEntity.ok().body(carUpdated);

                }).orElse(ResponseEntity.notFound().build());
    }
}

