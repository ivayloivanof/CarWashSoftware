package bg.car_wash.areas.car.serviceImpl;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.car.exception.CarDBEmptyException;
import bg.car_wash.areas.car.repository.CarRepository;
import bg.car_wash.areas.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Override
	public void createCar(Car car){
		this.carRepository.save(car);
	}

	@Override
	public List<Car> findAllCars() {
		List<Car> cars = this.carRepository.findAll();
		if (cars.isEmpty()) {
			throw new CarDBEmptyException("Not found cars in DB", 404);
		}

		return cars;
	}
}
