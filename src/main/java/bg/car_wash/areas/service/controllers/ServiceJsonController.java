package bg.car_wash.areas.service.controllers;

import bg.car_wash.areas.service.models.viewModel.ServiceViewModel;
import bg.car_wash.areas.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/json/service")
public class ServiceJsonController {

	private ServiceService serviceService;

	@Autowired
	public ServiceJsonController(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@GetMapping("/all")
	public ResponseEntity getAllServices() {
		List<ServiceViewModel> servicesViewModel = this.serviceService.findAllServices();

		if (servicesViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<ServiceViewModel>> responseEntityCar = new ResponseEntity(servicesViewModel, HttpStatus.OK);
		return responseEntityCar;
	}

}
