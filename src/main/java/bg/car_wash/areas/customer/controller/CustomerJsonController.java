package bg.car_wash.areas.customer.controller;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.models.viewModels.CustomerWithCarsViewModel;
import bg.car_wash.areas.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping("/json/customer")
public class CustomerJsonController {

	private CustomerService customerService;
	private ModelMapper modelMapper;

	@Autowired
	public CustomerJsonController(CustomerService customerService, ModelMapper modelMapper) {
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity getAllCustomers() {
		List<CustomerWithCarsViewModel> customersViewModels = new LinkedList<>();
		List<Customer> customers = this.customerService.findAllCustomers();
		for (Customer customer : customers) {
			customersViewModels.add(this.modelMapper.map(customer, CustomerWithCarsViewModel.class));
		}

		if (customersViewModels.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CustomerWithCarsViewModel>> responseEntityCar = new ResponseEntity<>(customersViewModels, HttpStatus.OK);
		return responseEntityCar;
	}

	@GetMapping("/info/{id}")
	public ResponseEntity getCustomerInfo(@PathVariable(value = "id") Long id) {
		Customer customer = this.customerService.findCustomerById(id);
		CustomerWithCarsViewModel customerViewModel = this.modelMapper.map(customer, CustomerWithCarsViewModel.class);

		if (customerViewModel == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CustomerWithCarsViewModel>> responseEntityCar = new ResponseEntity(customerViewModel, HttpStatus.OK);
		return responseEntityCar;
	}
}
