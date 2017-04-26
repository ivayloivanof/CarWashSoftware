package bg.car_wash.areas.activity.controllers;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.exceptions.ActivityNotUpdateException;
import bg.car_wash.areas.activity.models.bindingModel.ActivityBindingModel;
import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;
import bg.car_wash.areas.activity.service.ActivityService;
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
@RequestMapping("/activity")
public class ActivityController {

	private ActivityService activityService;

	private ModelMapper modelMapper;

	@Autowired
	public ActivityController(ActivityService activityService, ModelMapper modelMapper) {
		this.activityService = activityService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/add")
	public String getAddActivityPage(
			Model model,
			@Valid @ModelAttribute ActivityBindingModel activityBindingModel,
			BindingResult bindingResult) {
		model.addAttribute("pageTitle", PageTitle.ACTIVITY_ADD_PAGE);

		return "activity/activity-add";
	}

	@PostMapping("/add")
	public String addActivityPage(
			Model model,
			@Valid @ModelAttribute ActivityBindingModel activityBindingModel,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.ACTIVITY_ADD_PAGE);
			return "activity/activity-add";
		}

		Activity activity = this.modelMapper.map(activityBindingModel, Activity.class);

		this.activityService.createActivity(activity);

		return "redirect:all";
	}

	@GetMapping("/all")
	public String getAllActivityPage(Model model) {

		List<ActivityViewModel> activityViewModelList = new LinkedList<>();
		List<Activity> activities = this.activityService.findAllActivities();
		for (Activity activity : activities) {
			activityViewModelList.add(this.modelMapper.map(activity, ActivityViewModel.class));
		}

		model.addAttribute("pageTitle", PageTitle.ACTIVITY_ALL_PAGE);
		model.addAttribute("activitiesViewModel", activityViewModelList);

		return "activity/activity-all";
	}

	@GetMapping("/edit/{id}")
	public String getEditCustomerByIdPage(
			@PathVariable(value = "id") Long id,
			Model model,
			@Valid @ModelAttribute ActivityBindingModel activityBindingModel,
			BindingResult bindingResult) {

		model.addAttribute("pageTitle", PageTitle.ACTIVITY_EDIT_PAGE);
		model.addAttribute("activityViewModel", getActivityViewModel(id));

		return "activity/activity-edit";
	}

	@PostMapping("/edit/{id}")
	public String editCustomerByIdPage(
			@PathVariable(value = "id") Long id,
			Model model,
			@Valid @ModelAttribute ActivityBindingModel activityBindingModel,
			BindingResult bindingResult) throws ActivityNotUpdateException {

		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", PageTitle.ACTIVITY_EDIT_PAGE);
			model.addAttribute("customerViewModel", getActivityViewModel(id));

			return "activity/activity-edit";
		}

		Activity activity = this.modelMapper.map(activityBindingModel, Activity.class);
		this.activityService.updateActivity(activity);

		return "redirect:/activity/all";
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteActivity(@PathVariable(value = "id") Long id) {
		this.activityService.deleteActivityById(id);

		return "redirect:/activity/all";
	}

	private ActivityViewModel getActivityViewModel(Long id) {
		Activity activity = this.activityService.findActivityById(id);
		ActivityViewModel activityViewModel = this.modelMapper.map(activity, ActivityViewModel.class);

		return activityViewModel;
	}
}
