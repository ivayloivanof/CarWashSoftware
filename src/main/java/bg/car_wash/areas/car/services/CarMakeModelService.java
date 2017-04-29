package bg.car_wash.areas.car.services;

import bg.car_wash.areas.car.entities.CarMakeModel;
import bg.car_wash.areas.car.models.viewModel.CarMakeModelViewModel;
import bg.car_wash.areas.car.models.viewModel.CarMakeViewModel;
import bg.car_wash.areas.car.models.viewModel.CarModelViewModel;

import java.util.List;

public interface CarMakeModelService {
	void createCar(CarMakeModel car);

	List<CarMakeModelViewModel> findAllCarMakeModel();

	List<CarModelViewModel> findAllCarMakeModelByModel(String model, String make);

	List<CarMakeViewModel> findAllCarMakeModelByMake(String make);
}
