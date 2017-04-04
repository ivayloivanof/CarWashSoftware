package bg.car_wash.areas.car.controller;

import bg.car_wash.areas.car.models.bindingModel.CarBindingModel;
import bg.car_wash.configurations.site.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/car")
public class CarController {

	@GetMapping("/all")
	public String getAllCarsPage(Model model) {
		model.addAttribute("pageTitle", PageTitle.CAR_ALL_PAGE);
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
