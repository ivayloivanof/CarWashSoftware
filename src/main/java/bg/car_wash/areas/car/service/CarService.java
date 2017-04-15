package bg.car_wash.areas.car.service;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.car.exception.CarDBEmptyException;
import bg.car_wash.areas.car.exception.CarNotCreateException;
import bg.car_wash.areas.car.exception.CarNotFoundException;

import java.util.List;

public interface CarService {

	void createCar(Car car) throws CarNotCreateException;

	void updateCar(Car car) throws CarNotFoundException;

	List<Car> findAllCars() throws CarDBEmptyException;

	void deleteCarById(Long id) throws CarNotFoundException;

	Car findCarById(Long id) throws CarNotFoundException;
}
