package bg.car_wash.areas.car.services;

import bg.car_wash.areas.car.entities.Car;
import bg.car_wash.areas.car.exceptions.CarDBEmptyException;
import bg.car_wash.areas.car.exceptions.CarNotCreateException;
import bg.car_wash.areas.car.exceptions.CarNotFoundException;

import java.util.List;

public interface CarService {

	void createCar(Car car) throws CarNotCreateException;

	void updateCar(Car car) throws CarNotFoundException;

	List<Car> findAllCars() throws CarDBEmptyException;

	void deleteCarById(Long id) throws CarNotFoundException;

	Car findCarById(Long id) throws CarNotFoundException;

	List<Car> findCarByRegistrationNumber(String carRegistrationNumber);
}
