package bg.car_wash.areas.service.controller;

import bg.car_wash.areas.service.entity.Service;
import bg.car_wash.areas.service.models.bindingModel.ServiceBindingModel;
import bg.car_wash.areas.service.models.viewModel.ServiceViewModel;
import bg.car_wash.areas.service.service.ServiceService;
import bg.car_wash.configurations.site.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceController {

	private ServiceService serviceService;
	private ModelMapper modelMapper;

	@Autowired
	public ServiceController(ServiceService serviceService, ModelMapper modelMapper) {
		this.serviceService = serviceService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/add")
	public String getAddServicePage(
			Model model,
			@Valid @ModelAttribute ServiceBindingModel serviceBindingModel,
			BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.SERVICE_ADD_PAGE);

		return "service/service-add";
	}

	@PostMapping("/add")
	public String addServicePage(
			Model model,
			@Valid @ModelAttribute ServiceBindingModel serviceBindingModel,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.SERVICE_ADD_PAGE);
			return "service/service-add";
		}

		Service service = this.modelMapper.map(serviceBindingModel, Service.class);
		this.serviceService.createService(service);

		return "redirect:all";
	}

	@GetMapping("/all")
	public String getAllServicesPage(Model model) {

		List<ServiceViewModel> servicesViewModel = new LinkedList<>();
		List<Service> services = this.serviceService.findAllServices();
		for (Service service : services) {
			servicesViewModel.add(this.modelMapper.map(service, ServiceViewModel.class));
		}

		model.addAttribute("pageTitle", PageTitle.SERVICE_ALL_PAGE);
		model.addAttribute("servicesViewModel", servicesViewModel);

		return "service/service-all";
	}

}
