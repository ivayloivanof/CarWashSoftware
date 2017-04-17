package bg.car_wash.areas.car.service;

import bg.car_wash.areas.car.entity.CarMakeModel;

import java.util.List;

public interface CarMakeModelService {
	void createCar(CarMakeModel car);

	List<CarMakeModel> findAllCarMakeModel();
}
