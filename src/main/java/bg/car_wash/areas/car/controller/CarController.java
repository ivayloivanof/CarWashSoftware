package bg.car_wash.areas.car.controller;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.car.models.bindingModel.CarBindingModel;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.service.CarService;
import bg.car_wash.configurations.site.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;

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
	public String addCarForWorkPage(
			Model model,
			@Valid @ModelAttribute CarBindingModel carBindingModel,
			BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CAR_ADD_PAGE);
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

		//TODO get customer from DB
		Car car = this.modelMapper.map(carBindingModel, Car.class);
		car.setCarModelName(carBindingModel.getCarModel());

		this.carService.createCar(car);

		return "redirect:/car/all";
	}

	@GetMapping("/edit")
	public String editCarForWorkPage(Model model) {
		model.addAttribute("pageTitle", PageTitle.CAR_EDIT_PAGE);
		return "car/car-edit";
	}

	@GetMapping("/delete")
	public String deleteCarForWorkPage(Model model) {
		model.addAttribute("pageTitle", PageTitle.CAR_DELETE_PAGE);
		return "car/car-edit";
	}
}
