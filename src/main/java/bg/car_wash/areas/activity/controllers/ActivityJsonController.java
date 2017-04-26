package bg.car_wash.areas.activity.controllers;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;
import bg.car_wash.areas.activity.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/json/activity")
public class ActivityJsonController {

	private ActivityService activityService;
	private ModelMapper modelMapper;

	@Autowired
	public ActivityJsonController(ActivityService activityService, ModelMapper modelMapper) {
		this.activityService = activityService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/all")
	public ResponseEntity getAllActivities() {

		List<ActivityViewModel> activityViewModelList = new LinkedList<>();
		List<Activity> activities = this.activityService.findAllActivities();
		for (Activity activity : activities) {
			activityViewModelList.add(this.modelMapper.map(activity, ActivityViewModel.class));
		}

		if (activityViewModelList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<ActivityViewModel>> responseEntityCar = new ResponseEntity(activityViewModelList, HttpStatus.OK);

		return responseEntityCar;
	}
}
