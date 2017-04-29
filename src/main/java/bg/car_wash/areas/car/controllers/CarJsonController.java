package bg.car_wash.areas.car.controllers;

import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.services.CarService;
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
public class CarJsonController {

	private CarService carService;

	@Autowired
	public CarJsonController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<CarViewModel>> getAllCarsInJson() {
		List<CarViewModel> carViewModels = this.carService.findAllCars();

		if (carViewModels.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarViewModel>> responseEntityCar = new ResponseEntity(carViewModels, HttpStatus.OK);
		return responseEntityCar;
	}

	@GetMapping("/search/{carRegistrationNumber}")
	public ResponseEntity searchCarByRegistrationNumber(@PathVariable(value = "carRegistrationNumber") String carRegistrationNumber) {
		List<CarViewModel> carsViewModel = this.carService.findCarByRegistrationNumber(carRegistrationNumber);
		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

}
