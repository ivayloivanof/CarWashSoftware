package bg.car_wash.areas.car.servicesImpl;

import bg.car_wash.areas.car.entities.CarMakeModel;
import bg.car_wash.areas.car.repositories.CarMakeModelRepository;
import bg.car_wash.areas.car.services.CarMakeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarMakeModelServiceImpl implements CarMakeModelService {

	private CarMakeModelRepository carMakeModelRepository;

	@Autowired
	public CarMakeModelServiceImpl(CarMakeModelRepository carMakeModelRepository) {
		this.carMakeModelRepository = carMakeModelRepository;
	}

	@Override
	public void createCar(CarMakeModel car) {
		this.carMakeModelRepository.save(car);
	}

	@Override
	public List<CarMakeModel> findAllCarMakeModel() {
		List<CarMakeModel> carMakeModels = this.carMakeModelRepository.findAll();

		return carMakeModels;
	}

	@Override
	public List<CarMakeModel> findAllCarMakeModelByModel(String model, String make) {
		List<CarMakeModel> carMakeModels = this.carMakeModelRepository.findCarMakeModelByModelStartingWithAndMakeOrderByModel(model, make);
		return carMakeModels;
	}

	@Override
	public List<CarMakeModel> findAllCarMakeModelByMake(String make) {
		List<CarMakeModel> carMakeModels = this.carMakeModelRepository.findCarMakeModelByMakeStartingWithOrderByMake(make);
		return carMakeModels;
	}
}
