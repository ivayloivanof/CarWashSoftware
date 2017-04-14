package bg.car_wash.areas.activity.controller;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.models.bindingModel.ActivityBindingModel;
import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;
import bg.car_wash.areas.activity.service.ActivityService;
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

		if(bindingResult.hasErrors()) {
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
}
