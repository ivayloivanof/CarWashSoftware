package bg.car_wash.controller;

import bg.car_wash.configurations.site.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHomePage() {

		return "redirect:/car/all";
	}

	@GetMapping("/*")
	public String redirectAllInvalidPage() {

		return "redirect:/car/all";
	}


}
