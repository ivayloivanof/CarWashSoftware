package bg.car_wash.controllers;

import bg.car_wash.configurations.site.PageTitleNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("pageTitle", PageTitleNames.HOME_PAGE);
		return "home";
	}

}
