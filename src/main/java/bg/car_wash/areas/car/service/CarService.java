package bg.car_wash.areas.car.service;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.car.exception.CarDBEmptyException;
import bg.car_wash.areas.car.exception.CarNotCreateException;

import java.util.List;

public interface CarService {

	void createCar(Car car) throws CarNotCreateException;

	List<Car> findAllCars() throws CarDBEmptyException;

}
