package bg.car_wash.areas.car.controllers;

import bg.car_wash.areas.car.models.bindingModel.CarBindingModel;
import bg.car_wash.areas.car.models.bindingModel.CarSearchBindingModel;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.services.CarService;
import bg.car_wash.configurations.site.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/car")
public class CarController {

	private CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/all")
	public String getAllCarsPage(Model model) {
		model.addAttribute("pageTitle", PageTitle.CAR_ALL_PAGE);
		model.addAttribute("carViewModel", this.carService.findAllCars());
		model.addAttribute("customersViewModel", this.carService.getAllCustomers());

		return "car/car-all";
	}

	@GetMapping("/add")
	public String addCarForWorkPage(Model model, @Valid @ModelAttribute CarBindingModel carBindingModel, BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CAR_ADD_PAGE);
		model.addAttribute("customersViewModel", this.carService.getAllCustomers());

		return "car/car-add";
	}

	@PostMapping("/add")
	public String addCar(Model model, @Valid @ModelAttribute CarBindingModel carBindingModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.CAR_ADD_PAGE);
			model.addAttribute("customersViewModel", this.carService.getAllCustomers());

			return "car/car-add";
		}

		this.carService.createCar(carBindingModel);

		return "redirect:/car/all";
	}

	@GetMapping("/edit")
	public String getEditCarPage(Model model, @Valid @ModelAttribute CarSearchBindingModel carSearchBindingModel, BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);
		return "car/car-edit-search";
	}

	@GetMapping("/edit/{id}")
	public String getEditCarByIdPage(@PathVariable(value = "id") Long id, Model model, @Valid @ModelAttribute CarBindingModel carBindingModel, BindingResult bindingResult) {

		CarViewModel carViewModel = this.carService.findCarById(id);

		model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);
		model.addAttribute("car", carViewModel);

		return "car/car-edit";
	}

	@PostMapping("/edit/{id}")
	public String editCarById(@PathVariable(value = "id") Long id, Model model, @Valid @ModelAttribute CarBindingModel carBindingModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);
			return "car/car-edit";
		}

		this.carService.updateCar(carBindingModel);

		return "redirect:/car/all";
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteCar(@PathVariable(value = "id") Long id) {
		this.carService.deleteCarById(id);

		return "redirect:/car/all";
	}
}
