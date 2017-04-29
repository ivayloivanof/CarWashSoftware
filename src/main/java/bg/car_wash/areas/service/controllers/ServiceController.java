package bg.car_wash.areas.service.controllers;

import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;
import bg.car_wash.areas.activity.service.ActivityService;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.services.CarService;
import bg.car_wash.areas.service.models.bindingModel.ServiceBindingModel;
import bg.car_wash.areas.service.models.viewModel.ServiceViewModel;
import bg.car_wash.areas.service.service.ServiceService;
import bg.car_wash.configurations.site.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceController {

	private ServiceService serviceService;
	private ActivityService activityService;
	private CarService carService;

	@Autowired
	public ServiceController(
			ServiceService serviceService,
			ActivityService activityService,
			CarService carService) {
		this.serviceService = serviceService;
		this.activityService = activityService;
		this.carService = carService;
	}

	@GetMapping("/add")
	public String getAddServicePage(Model model, @Valid @ModelAttribute ServiceBindingModel serviceBindingModel, BindingResult bindingResult) {
		List<ActivityViewModel> activityViewModel = this.activityService.findAllActivities();
		List<CarViewModel> carViewModels = this.carService.findAllCars();

		model.addAttribute("pageTitle", PageTitle.SERVICE_ADD_PAGE);
		model.addAttribute("activityViewModel", activityViewModel);
		model.addAttribute("carViewModel", carViewModels);

		return "service/service-add";
	}

	@PostMapping("/add")
	public String addServicePage(Model model, @Valid @ModelAttribute ServiceBindingModel serviceBindingModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.SERVICE_ADD_PAGE);
			return "service/service-add";
		}

		this.serviceService.createService(serviceBindingModel);

		return "redirect:all";
	}

	@GetMapping("/all")
	public String getAllServicesPage(Model model) {
		List<ServiceViewModel> servicesViewModel = this.serviceService.findAllServices();

		model.addAttribute("pageTitle", PageTitle.SERVICE_ALL_PAGE);
		model.addAttribute("servicesViewModel", servicesViewModel);

		return "service/service-all";
	}

	@GetMapping("/edit/{id}")
	public String getEditServiceByIdPage(@PathVariable(value = "id") Long id, Model model, @Valid @ModelAttribute ServiceBindingModel serviceBindingModel, BindingResult bindingResult) {

		model.addAttribute("pageTitle", PageTitle.SERVICE_EDIT_PAGE);
		model.addAttribute("serviceViewModel", this.serviceService.getServiceViewModel(id));

		return "service/service-edit";
	}

	@PostMapping("/edit/{id}")
	public String editServiceByIdPage(@PathVariable(value = "id") Long id, Model model, @Valid @ModelAttribute ServiceBindingModel serviceBindingModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.SERVICE_EDIT_PAGE);
			model.addAttribute("serviceViewModel", this.serviceService.getServiceViewModel(id));

			return "service/service-edit";
		}

		this.serviceService.updateService(serviceBindingModel);

		return "redirect:/service/all";
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteService(@PathVariable(value = "id") Long id) {
		this.serviceService.deleteServiceById(id);

		return "redirect:/service/all";
	}

}
