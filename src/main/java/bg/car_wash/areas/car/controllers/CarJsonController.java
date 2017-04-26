package bg.car_wash.areas.car.controllers;

import bg.car_wash.areas.car.entities.Car;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.services.CarService;
import bg.car_wash.areas.customer.service.CustomerService;
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
public class CarJsonController {

	private CarService carService;
	private CustomerService customerService;
	private ModelMapper modelMapper;

	@Autowired
	public CarJsonController(CarService carService, CustomerService customerService, ModelMapper modelMapper) {
		this.carService = carService;
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/all")
	public ResponseEntity<List<CarViewModel>> getAllCarsInJson() {
		List<CarViewModel> carViewModels = new LinkedList<>();
		List<Car> cars = this.carService.findAllCars();
		for (Car car : cars) {
			carViewModels.add(this.modelMapper.map(car, CarViewModel.class));
		}

		if (carViewModels.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarViewModel>> responseEntityCar = new ResponseEntity(carViewModels, HttpStatus.OK);
		return responseEntityCar;
	}

	@GetMapping("/search/{carRegistrationNumber}")
	public ResponseEntity searchCarByRegistrationNumber(@PathVariable(value = "carRegistrationNumber") String carRegistrationNumber) {
		List<Car> cars = this.carService.findCarByRegistrationNumber(carRegistrationNumber);
		List<CarViewModel> carsViewModel = new LinkedList<>();
		for (Car car : cars) {
			carsViewModel.add(this.modelMapper.map(car, CarViewModel.class));
		}

		if (carsViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CarViewModel>> responseEntityCar = new ResponseEntity(carsViewModel, HttpStatus.OK);

		return responseEntityCar;
	}

}
