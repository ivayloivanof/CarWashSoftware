package bg.car_wash.areas.activity.serviceImpl;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.exception.ActivityDBEmptyException;
import bg.car_wash.areas.activity.exception.ActivityNotCreateException;
import bg.car_wash.areas.activity.exception.ActivityNotFoundException;
import bg.car_wash.areas.activity.exception.ActivityNotUpdateException;
import bg.car_wash.areas.activity.repository.ActivityRepository;
import bg.car_wash.areas.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;

	@Autowired
	public ActivityServiceImpl(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}

	@Override
	public void createActivity(Activity activity) throws ActivityNotCreateException {
		if (activity == null) {
			throw new ActivityNotCreateException("Activity can not create!");
		}

		this.activityRepository.save(activity);
	}

	@Override
	public List<Activity> findAllActivities() throws ActivityDBEmptyException {
		List<Activity> activities = this.activityRepository.findAll();
		if(activities.isEmpty()) {
			throw  new ActivityDBEmptyException("Activity database is empty!");
		}

		return activities;
	}

	@Override
	public Activity findActivityByName(String name) throws ActivityNotFoundException {
		Activity activity = this.activityRepository.findByActivityName(name);
		if (activity == null) {
			throw new ActivityNotFoundException("Activity with name is not found in database");
		}

		return activity;
	}

	@Override
	public Activity findActivityById(Long id) throws ActivityNotFoundException {
		Activity activity = this.activityRepository.findActivityById(id);
		if (activity == null) {
			throw new ActivityNotFoundException("This activity is not found in database by id!");
		}

		return activity;
	}

	@Override
	public void updateActivity(Activity activity) throws ActivityNotUpdateException {
		if (activity == null) {
			throw new ActivityNotUpdateException("Activity can not update");
		}

		this.activityRepository.save(activity);
	}

	@Override
	public void deleteActivityById(Long id) throws ActivityNotFoundException {
		if(id == null) {
			throw new ActivityNotFoundException("Activity id can not delete");
		}

		this.activityRepository.deleteActivityById(id);
	}
}
