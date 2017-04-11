package bg.car_wash.areas.customer.controller;

import bg.car_wash.areas.customer.models.bindingModel.CustomerBindingModel;
import bg.car_wash.areas.customer.service.CustomerService;
import bg.car_wash.configurations.site.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/add")
	public String addCarForWorkPage(
			Model model,
			@Valid @ModelAttribute CustomerBindingModel customerBindingModel,
			BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CUSTOMER_ADD_PAGE);

		return "customer/customer-add";
	}
}
