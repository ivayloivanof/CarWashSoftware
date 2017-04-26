package bg.car_wash.areas.car.controllers;

import bg.car_wash.areas.car.entities.CarMakeModel;
import bg.car_wash.areas.car.models.viewModel.CarMakeViewModel;
import bg.car_wash.areas.car.models.viewModel.CarModelViewModel;
import bg.car_wash.areas.car.services.CarMakeModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/json/car")
public class CarMakeModelJsonController {

	private CarMakeModelService carMakeModelService;
	private ModelMapper modelMapper;

	@Autowired
	public CarMakeModelJsonController(CarMakeModelService carMakeModelService, ModelMapper modelMapper) {
		this.carMakeModelService = carMakeModelService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/make/all")
	public ResponseEntity getAllCarsMakeInJson() {

		List<CarMakeModel> carsMakeModel = this.carMakeModelService.findAllCarMakeModel();
		List<CarMakeViewModel> carsViewModel = new LinkedList<>();
		for (CarMakeModel carMakeModel : carsMakeModel) {
			carsViewModel.add(this.modelMapper.map(carMakeModel, CarMakeViewModel.class));
		}

		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarMakeViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

	@GetMapping("/model/all")
	public ResponseEntity getAllCarsModelInJson() {

		List<CarMakeModel> carsMakeModel = this.carMakeModelService.findAllCarMakeModel();
		List<CarModelViewModel> carsViewModel = new LinkedList<>();
		for (CarMakeModel carMakeModel : carsMakeModel) {
			carsViewModel.add(this.modelMapper.map(carMakeModel, CarModelViewModel.class));
		}

		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarMakeViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

	@GetMapping("/{make}/model/{model}")
	public ResponseEntity getAllCarModelsInJson(@PathVariable(value = "make") String make, @PathVariable(value = "model") String model) {

		List<CarMakeModel> carsMakeModel = this.carMakeModelService.findAllCarMakeModelByModel(model, make);
		List<CarModelViewModel> carsViewModel = new LinkedList<>();
		for (CarMakeModel carMakeModel : carsMakeModel) {
			carsViewModel.add(this.modelMapper.map(carMakeModel, CarModelViewModel.class));
		}

		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarMakeViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

	@GetMapping("/make/{make}")
	public ResponseEntity getAllCarMakesInJson(@PathVariable(value = "make") String make) {

		List<CarMakeModel> carsMakeModel = this.carMakeModelService.findAllCarMakeModelByMake(make);
		List<CarMakeViewModel> carsViewModel = new LinkedList<>();
		for (CarMakeModel carMakeModel : carsMakeModel) {
			carsViewModel.add(this.modelMapper.map(carMakeModel, CarMakeViewModel.class));
		}

		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarMakeViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

}
