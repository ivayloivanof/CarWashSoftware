package bg.car_wash.areas.customer.controller;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.models.bindingModel.CustomerBindingModel;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;
import bg.car_wash.areas.customer.models.viewModels.CustomerWithCarsViewModel;
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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService customerService;
	private ModelMapper modelMapper;

	@Autowired
	public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/add")
	public String getAddCustomerPage(
			Model model,
			@Valid @ModelAttribute CustomerBindingModel customerBindingModel,
			BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.CUSTOMER_ADD_PAGE);

		return "customer/customer-add";
	}

	@PostMapping("/add")
	public String addCustomerPage(
			Model model,
			@Valid @ModelAttribute CustomerBindingModel customerBindingModel,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.CUSTOMER_ADD_PAGE);
			return "customer/customer-add";
		}

		customerBindingModel.setDate(new Date());

		Customer customer = this.modelMapper.map(customerBindingModel, Customer.class);

		this.customerService.createCustomer(customer);

		return "redirect:all";
	}

	@GetMapping("/all")
	public String getAllCustomerPage(Model model) {
		List<CustomerViewModel> customersViewModels = new LinkedList<>();
		List<Customer> customers = this.customerService.findAllCustomers();
		for (Customer customer : customers) {
			customersViewModels.add(this.modelMapper.map(customer, CustomerViewModel.class));
		}

		model.addAttribute("pageTitle", PageTitle.CUSTOMER_ALL_PAGE);
		model.addAttribute("customersViewModel", customersViewModels);

		return "customer/customer-all";
	}

	@GetMapping("/info/{id}")
	public String getCustomerInfoPage(@PathVariable(value = "id") Long id, Model model) {
		Customer customer = this.customerService.findCustomerById(id);
		CustomerWithCarsViewModel customerViewModel = this.modelMapper.map(customer, CustomerWithCarsViewModel.class);

		model.addAttribute("pageTitle", PageTitle.CUSTOMER_INFO_PAGE);
		model.addAttribute("customerViewModel", customerViewModel);

		return "customer/customer-info";
	}

	@GetMapping("/edit/{id}")
	public String getEditCustomerByIdPage(
			@PathVariable(value = "id") Long id,
			Model model,
			@Valid @ModelAttribute CustomerBindingModel customerBindingModel,
			BindingResult bindingResult) {

		model.addAttribute("pageTitle", PageTitle.CUSTOMER_EDIT_PAGE);
		model.addAttribute("customerViewModel", getCustomerViewModel(id));

		return "customer/customer-edit";
	}

	@PostMapping("/edit/{id}")
	public String editCustomerByIdPage(
			@PathVariable(value = "id") Long id,
			Model model,
			@Valid @ModelAttribute CustomerBindingModel customerBindingModel,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.CUSTOMER_EDIT_PAGE);
			model.addAttribute("customerViewModel", getCustomerViewModel(id));

			return "customer/customer-edit";
		}

		Customer customer = this.modelMapper.map(customerBindingModel, Customer.class);
		customer.setDate(new Date());
		this.customerService.updateCustomer(customer);

		return "redirect:/customer/all";
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteCustomer(@PathVariable(value = "id") Long id) {
		this.customerService.deleteCustomerById(id);

		return "redirect:/customer/all";
	}

	private CustomerViewModel getCustomerViewModel(Long id) {
		Customer customer = this.customerService.findCustomerById(id);
		CustomerViewModel customerViewModel = this.modelMapper.map(customer, CustomerViewModel.class);

		return customerViewModel;
	}
}
