package bg.car_wash.areas.service.controller;

import bg.car_wash.areas.service.entity.Service;
import bg.car_wash.areas.service.models.viewModel.ServiceViewModel;
import bg.car_wash.areas.service.service.ServiceService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/json/service")
public class ServiceJsonController {

	private ServiceService serviceService;
	private ModelMapper modelMapper;

	@Autowired
	public ServiceJsonController(ServiceService serviceService, ModelMapper modelMapper) {
		this.serviceService = serviceService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity getAllServices() {

		List<ServiceViewModel> servicesViewModel = new LinkedList<>();
		List<Service> services = this.serviceService.findAllServices();
		for (Service service : services) {
			servicesViewModel.add(this.modelMapper.map(service, ServiceViewModel.class));
		}

		if(servicesViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<ServiceViewModel>> responseEntityCar = new ResponseEntity(servicesViewModel, HttpStatus.OK);
		return responseEntityCar;
	}

}
