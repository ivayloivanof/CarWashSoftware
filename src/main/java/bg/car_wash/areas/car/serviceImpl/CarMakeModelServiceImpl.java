package bg.car_wash.areas.car.serviceImpl;

import bg.car_wash.areas.car.entity.CarMakeModel;
import bg.car_wash.areas.car.repository.CarMakeModelRepository;
import bg.car_wash.areas.car.service.CarMakeModelService;
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
}
