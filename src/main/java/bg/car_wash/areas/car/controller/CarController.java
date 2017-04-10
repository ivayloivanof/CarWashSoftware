package bg.car_wash.areas.car.controller;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.car.models.bindingModel.CarBindingModel;
import bg.car_wash.areas.car.models.bindingModel.CarSearchBindingModel;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.service.CarService;
import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.service.CustomerService;
import bg.car_wash.configurations.site.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/all")
	public String getAllCarsPage(Model model) {
		model.addAttribute("pageTitle", PageTitle.CAR_ALL_PAGE);

		List<Car> cars = this.carService.findAllCars();
		List<CarViewModel> carsViewModel = new LinkedList<>();
		for (Car car : cars) {
			carsViewModel.add(this.modelMapper.map(car, CarViewModel.class));
		}

		model.addAttribute("carViewModel", carsViewModel);

		return "car/car-all";
	}

	@GetMapping("/add")
	public String addCarForWorkPage(Model model,
			@Valid @ModelAttribute CarBindingModel carBindingModel,
									BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CAR_ADD_PAGE);
		//TODO add customer form
		return "car/car-add";
	}

	@PostMapping("/add")
	public String addCar(
			Model model,
			@Valid @ModelAttribute CarBindingModel carBindingModel,
			BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CAR_ADD_PAGE);

		if(bindingResult.hasErrors()) {
			return "car/car-add";
		}

		//TODO get customer from DB and load in form
//		Customer customer = this.customerService.findCustomerByName(carBindingModel.getOwner().getName());

		Car car = this.modelMapper.map(carBindingModel, Car.class);
		car.setCarModelName(carBindingModel.getCarModel());

		this.carService.createCar(car);

		return "redirect:/car/all";
	}

	@GetMapping("/edit")
	public String getEditCarPage(
			Model model,
			@Valid @ModelAttribute CarSearchBindingModel carSearchBindingModel,
			BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);
		return "car/car-edit-search";
	}

	@PostMapping("/edit")
	public String editCarPage(
			Model model,
			@Valid @ModelAttribute CarSearchBindingModel carSearchBindingModel,
			BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);


		return "car/car-edit-search";
	}

	@GetMapping("/edit/{id}")
	public String getEditCarByIdPage(
			@RequestParam("id") Long id,
			Model model) {
		//TODO get car from db and load in form
		Car car = this.carService.findCarById(id);


		model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);
		return "car/car-edit";
	}

	@PostMapping("/edit/{id}")
	public String editCarById(
			@RequestParam("id") Long id,
			@Valid @ModelAttribute CarBindingModel carBindingModel,
			BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			return "car/car-edit";
		}

		return null;
	}

	@PostMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteCar(@RequestParam("id") Long id) {
		this.carService.deleteCarById(id);

		return "redirect: car/all";
	}
}
