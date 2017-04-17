package bg.car_wash.areas.car.serviceImpl;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.car.exception.CarDBEmptyException;
import bg.car_wash.areas.car.exception.CarNotCreateException;
import bg.car_wash.areas.car.exception.CarNotFoundException;
import bg.car_wash.areas.car.repository.CarRepository;
import bg.car_wash.areas.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

	private CarRepository carRepository;

	@Autowired
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public void createCar(Car car) throws CarNotCreateException {
		if (car == null) {
			throw new CarNotCreateException("Car not create when Car object is null!");
		}
		this.carRepository.save(car);
	}

	@Override
	public void updateCar(Car car) throws CarNotFoundException {
		this.carRepository.save(car);
	}

	@Override
	public List<Car> findAllCars() {
		List<Car> cars = this.carRepository.findAll();
		if (cars.isEmpty()) {
			throw new CarDBEmptyException("Car database is empty!");
		}

		return cars;
	}

	@Override
	public void deleteCarById(Long id) throws CarNotFoundException {
		if (id == null || id < 1) {
			throw new CarNotFoundException("Car not found in database!");
		}

		this.carRepository.deleteCarById(id);
	}

	@Override
	public Car findCarById(Long id) throws CarNotFoundException {
		Car car = this.carRepository.findCarById(id);
		if (car == null) {
			throw new CarNotFoundException("Car not found in database!");
		}

		return car;
	}
}
