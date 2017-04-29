package bg.car_wash.areas.car.servicesImpl;

import bg.car_wash.areas.car.entities.CarMakeModel;
import bg.car_wash.areas.car.models.viewModel.CarMakeModelViewModel;
import bg.car_wash.areas.car.models.viewModel.CarMakeViewModel;
import bg.car_wash.areas.car.models.viewModel.CarModelViewModel;
import bg.car_wash.areas.car.repositories.CarMakeModelRepository;
import bg.car_wash.areas.car.services.CarMakeModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarMakeModelServiceImpl implements CarMakeModelService {

	private CarMakeModelRepository carMakeModelRepository;
	private ModelMapper modelMapper;

	@Autowired
	public CarMakeModelServiceImpl(CarMakeModelRepository carMakeModelRepository, ModelMapper modelMapper) {
		this.carMakeModelRepository = carMakeModelRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createCar(CarMakeModel car) {
		this.carMakeModelRepository.save(car);
	}

	@Override
	public List<CarMakeModelViewModel> findAllCarMakeModel() {
		List<CarMakeModel> carsMakeModels = this.carMakeModelRepository.findAll();
		List<CarMakeModelViewModel> carsViewModel = new LinkedList<>();
		for (CarMakeModel carMakeModel : carsMakeModels) {
			carsViewModel.add(this.modelMapper.map(carMakeModel, CarMakeModelViewModel.class));
		}

		return carsViewModel;
	}

	@Override
	public List<CarModelViewModel> findAllCarMakeModelByModel(String model, String make) {
		List<CarMakeModel> carsMakeModel = this.carMakeModelRepository.findCarMakeModelByModelStartingWithAndMakeOrderByModel(model, make);

		List<CarModelViewModel> carsViewModel = new LinkedList<>();
		for (CarMakeModel carMakeModel : carsMakeModel) {
			carsViewModel.add(this.modelMapper.map(carMakeModel, CarModelViewModel.class));
		}

		return carsViewModel;
	}

	@Override
	public List<CarMakeViewModel> findAllCarMakeModelByMake(String make) {
		List<CarMakeModel> carsMakeModel = this.carMakeModelRepository.findCarMakeModelByMakeStartingWithOrderByMake(make);
		List<CarMakeViewModel> carsViewModel = new LinkedList<>();
		for (CarMakeModel carMakeModel : carsMakeModel) {
			carsViewModel.add(this.modelMapper.map(carMakeModel, CarMakeViewModel.class));
		}
		return carsViewModel;
	}
}
