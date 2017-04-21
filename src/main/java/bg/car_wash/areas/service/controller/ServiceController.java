package bg.car_wash.areas.service.controller;

import bg.car_wash.areas.service.entity.Service;
import bg.car_wash.areas.service.models.bindingModel.ServiceBindingModel;
import bg.car_wash.areas.service.models.viewModel.ServiceViewModel;
import bg.car_wash.areas.service.service.ServiceService;
import bg.car_wash.configurations.site.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/edit/{id}")
	public String getEditServiceByIdPage(
			@PathVariable(value = "id") Long id,
			Model model,
			@Valid @ModelAttribute ServiceBindingModel serviceBindingModel,
			BindingResult bindingResult) {

		model.addAttribute("pageTitle", PageTitle.SERVICE_EDIT_PAGE);
		model.addAttribute("serviceViewModel", getServiceViewModel(id));

		return "service/service-edit";
	}

	@PostMapping("/edit/{id}")
	public String editServiceByIdPage(
			@PathVariable(value = "id") Long id,
			Model model,
			@Valid @ModelAttribute ServiceBindingModel serviceBindingModel,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.SERVICE_EDIT_PAGE);
			model.addAttribute("serviceViewModel", getServiceViewModel(id));

			return "service/service-edit";
		}

		Service service = this.modelMapper.map(serviceBindingModel, Service.class);
		this.serviceService.updateService(service);

		return "redirect:/service/all";
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteService(@PathVariable(value = "id") Long id) {
		this.serviceService.deleteServiceById(id);

		return "redirect:/service/all";
	}

	private ServiceViewModel getServiceViewModel(Long id) {
		Service service = this.serviceService.findServiceById(id);
		ServiceViewModel serviceViewModel = this.modelMapper.map(service, ServiceViewModel.class);

		return serviceViewModel;
	}

}
