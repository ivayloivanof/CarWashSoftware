package bg.car_wash.areas.car.controller;

import bg.car_wash.configurations.site.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class CarController {

	@GetMapping("/all")
	public String getAllCarsPage(Model model) {
		model.addAttribute("pageTitle", PageTitle.CAR_ALL_PAGE);
		return "car/car-all";
	}

	@GetMapping("/add")
	public String addCarForWorkPage(Model model) {
		model.addAttribute("pageTitle", PageTitle.CAR_ADD_FOR_WORK_PAGE);
		return "car/car-add";
	}
}
