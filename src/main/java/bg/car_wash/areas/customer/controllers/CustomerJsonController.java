package bg.car_wash.areas.customer.controllers;

import bg.car_wash.areas.customer.models.viewModels.CustomerWithCarsViewModel;
import bg.car_wash.areas.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/json/customer")
public class CustomerJsonController {

	private CustomerService customerService;

	@Autowired
	public CustomerJsonController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/all")
	public ResponseEntity getAllCustomers() {
		List<CustomerWithCarsViewModel> customersViewModels = this.customerService.findAllCustomersWithCars();
		if (customersViewModels.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CustomerWithCarsViewModel>> responseEntityCar = new ResponseEntity<>(customersViewModels, HttpStatus.OK);

		return responseEntityCar;
	}

	@GetMapping("/info/{id}")
	public ResponseEntity getCustomerInfo(@PathVariable(value = "id") Long id) {
		CustomerWithCarsViewModel customerViewModel = this.customerService.findCustomerById(id);
		if (customerViewModel == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<CustomerWithCarsViewModel>> responseEntityCar = new ResponseEntity(customerViewModel, HttpStatus.OK);

		return responseEntityCar;
	}
}
