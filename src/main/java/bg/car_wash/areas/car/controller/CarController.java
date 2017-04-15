package bg.car_wash.areas.car.controller;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.car.models.bindingModel.CarBindingModel;
import bg.car_wash.areas.car.models.bindingModel.CarSearchBindingModel;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.service.CarService;
import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.models.bindingModel.CustomerAddFormBindingModel;
import bg.car_wash.areas.customer.models.bindingModel.CustomerBindingModel;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;
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

	private CarService carService;
	private CustomerService customerService;
	private ModelMapper modelMapper;

	@Autowired
	public CarController(CarService carService, CustomerService customerService, ModelMapper modelMapper) {
		this.carService = carService;
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/all")
	public String getAllCarsPage(Model model) {
		model.addAttribute("pageTitle", PageTitle.CAR_ALL_PAGE);

		List<Car> cars = this.carService.findAllCars();
		List<CarViewModel> carsViewModel = new LinkedList<>();
		for (Car car : cars) {
			carsViewModel.add(this.modelMapper.map(car, CarViewModel.class));
		}

		model.addAttribute("carViewModel", carsViewModel);
		model.addAttribute("customersViewModel", this.getAllCustomers());

		return "car/car-all";
	}

	@GetMapping("/add")
	public String addCarForWorkPage(
			Model model,
			@Valid @ModelAttribute CarBindingModel carBindingModel,
			BindingResult bindingResult) {

		model.addAttribute("pageTitle", PageTitle.CAR_ADD_PAGE);
		model.addAttribute("customersViewModel", this.getAllCustomers());

		return "car/car-add";
	}

	@PostMapping("/add")
	public String addCar(
			Model model,
			@Valid @ModelAttribute CarBindingModel carBindingModel,
			BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.CAR_ADD_PAGE);
			model.addAttribute("customersViewModel", this.getAllCustomers());

			return "car/car-add";
		}

		Customer customer = this.customerService.findCustomerById(carBindingModel.getCustomerId());
		Car car = this.modelMapper.map(carBindingModel, Car.class);

		car.setCarModelName(carBindingModel.getCarModel());
		car.setCarRegistrationNumber(car.getCarRegistrationNumber().toUpperCase());
		car.setOwner(customer);

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

		//TODO return edit car page with AJAX

		return "car/car-edit-search";
	}

	@GetMapping("/edit/{id}")
	public String getEditCarByIdPage(
			@PathVariable(value = "id") Long id,
			Model model,
			@Valid @ModelAttribute CarBindingModel carBindingModel,
			BindingResult bindingResult) {

		Car car = this.carService.findCarById(id);
		CarViewModel carViewModel = this.modelMapper.map(car, CarViewModel.class);

		model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);
		model.addAttribute("car", carViewModel);

		return "car/car-edit";
	}

	@PostMapping("/edit/{id}")
	public String editCarById(
			@PathVariable(value = "id") Long id,
			Model model,
			@Valid @ModelAttribute CarBindingModel carBindingModel,
			BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);
			return "car/car-edit";
		}
		Customer customer;
		if (carBindingModel.getCustomerId() != null) {
			customer = this.customerService.findCustomerById(carBindingModel.getCustomerId());
		} else {
			customer = this.customerService.findCustomerById(1L);
		}

		carBindingModel.setOwner(this.modelMapper.map(customer, CustomerViewModel.class));

		Car car = this.modelMapper.map(carBindingModel, Car.class);
		car.setCarModelName(carBindingModel.getCarModel());
		car.setCarRegistrationNumber(car.getCarRegistrationNumber().toUpperCase());

		this.carService.updateCar(car);

		return "redirect:/car/all";
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteCar(@PathVariable(value = "id") Long id) {
		this.carService.deleteCarById(id);

		return "redirect:/car/all";
	}

	private List<CustomerViewModel> getAllCustomers() {
		List<Customer> customers = this.customerService.findAllCustomers();
		List<CustomerViewModel> customerViewModels = new LinkedList<>();
		for (Customer customer : customers) {
			customerViewModels.add(this.modelMapper.map(customer, CustomerViewModel.class));
		}

		return customerViewModels;
	}
}
