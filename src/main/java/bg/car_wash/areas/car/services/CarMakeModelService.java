package bg.car_wash.areas.car.services;

import bg.car_wash.areas.car.entities.CarMakeModel;

import java.util.List;

public interface CarMakeModelService {
	void createCar(CarMakeModel car);

	List<CarMakeModel> findAllCarMakeModel();

	List<CarMakeModel> findAllCarMakeModelByModel(String model, String make);

	List<CarMakeModel> findAllCarMakeModelByMake(String make);
}
