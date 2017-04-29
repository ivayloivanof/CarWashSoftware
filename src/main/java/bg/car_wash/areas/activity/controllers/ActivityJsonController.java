package bg.car_wash.areas.activity.controllers;

import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;
import bg.car_wash.areas.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/json/activity")
public class ActivityJsonController {

	private ActivityService activityService;

	@Autowired
	public ActivityJsonController(ActivityService activityService) {
		this.activityService = activityService;
	}

	@GetMapping("/all")
	public ResponseEntity getAllActivities() {

		List<ActivityViewModel> activityViewModel = this.activityService.findAllActivities();

		if (activityViewModel.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<List<ActivityViewModel>> responseEntityCar = new ResponseEntity(activityViewModel, HttpStatus.OK);

		return responseEntityCar;
	}
}
