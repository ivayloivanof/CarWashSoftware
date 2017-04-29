package bg.car_wash.areas.car.controllers;

import bg.car_wash.areas.car.models.viewModel.CarMakeModelViewModel;
import bg.car_wash.areas.car.models.viewModel.CarMakeViewModel;
import bg.car_wash.areas.car.models.viewModel.CarModelViewModel;
import bg.car_wash.areas.car.services.CarMakeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json/car")
public class CarMakeModelJsonController {

	private CarMakeModelService carMakeModelService;

	@Autowired
	public CarMakeModelJsonController(CarMakeModelService carMakeModelService) {
		this.carMakeModelService = carMakeModelService;
	}

	@GetMapping("/make/all")
	public ResponseEntity getAllCarsMakeInJson() {
		List<CarMakeModelViewModel> carsViewModel = this.carMakeModelService.findAllCarMakeModel();
		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarMakeViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

	@GetMapping("/model/all")
	public ResponseEntity getAllCarsModelInJson() {
		List<CarMakeModelViewModel> carsViewModel = this.carMakeModelService.findAllCarMakeModel();
		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarMakeViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

	@GetMapping("/{make}/model/{model}")
	public ResponseEntity getAllCarModelsInJson(@PathVariable(value = "make") String make, @PathVariable(value = "model") String model) {
		List<CarModelViewModel> carsViewModel = this.carMakeModelService.findAllCarMakeModelByModel(model, make);
		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarMakeViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

	@GetMapping("/make/{make}")
	public ResponseEntity getAllCarMakesInJson(@PathVariable(value = "make") String make) {
		List<CarMakeViewModel> carsViewModel = this.carMakeModelService.findAllCarMakeModelByMake(make);
		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarMakeViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

}
