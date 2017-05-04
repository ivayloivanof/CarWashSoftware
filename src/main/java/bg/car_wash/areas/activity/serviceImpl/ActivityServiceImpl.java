package bg.car_wash.areas.activity.serviceImpl;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.exceptions.ActivityDBEmptyException;
import bg.car_wash.areas.activity.exceptions.ActivityNotCreateException;
import bg.car_wash.areas.activity.exceptions.ActivityNotFoundException;
import bg.car_wash.areas.activity.exceptions.ActivityNotUpdateException;
import bg.car_wash.areas.activity.models.bindingModel.ActivityBindingModel;
import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;
import bg.car_wash.areas.activity.repository.ActivityRepository;
import bg.car_wash.areas.activity.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

	private ActivityRepository activityRepository;
	private ModelMapper modelMapper;

	@Autowired
	public ActivityServiceImpl(ActivityRepository activityRepository, ModelMapper modelMapper) {
		this.activityRepository = activityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createActivity(ActivityBindingModel activityBindingModel) throws ActivityNotCreateException {
		if (activityBindingModel == null) {
			throw new ActivityNotCreateException("Activity can not create!");
		}

		Activity activity = this.modelMapper.map(activityBindingModel, Activity.class);
		this.activityRepository.save(activity);
	}

	@Override
	public List<ActivityViewModel> findAllActivities() throws ActivityDBEmptyException {
		List<Activity> activities = this.activityRepository.findAll();

		if (activities.isEmpty()) {
			throw new ActivityDBEmptyException("Activity database is empty!");
		}

		List<ActivityViewModel> activityViewModel = new LinkedList<>();

		for (Activity activity : activities) {
			activityViewModel.add(this.modelMapper.map(activity, ActivityViewModel.class));
		}

		return activityViewModel;
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
	public ActivityViewModel findActivityById(Long id) throws ActivityNotFoundException {
		Activity activity = this.activityRepository.findActivityById(id);
		if (activity == null) {
			throw new ActivityNotFoundException("This activity is not found in database by id!");
		}

		ActivityViewModel activityViewModel = this.modelMapper.map(activity, ActivityViewModel.class);

		return activityViewModel;
	}

	@Override
	public void updateActivity(ActivityBindingModel activityBindingModel) throws ActivityNotUpdateException {
		if (activityBindingModel == null) {
			throw new ActivityNotUpdateException("Activity can not update");
		}

		Activity activity = this.modelMapper.map(activityBindingModel, Activity.class);
		this.activityRepository.save(activity);
	}

	@Override
	public void deleteActivityById(Long id) throws ActivityNotFoundException {
		if (id == null) {
			throw new ActivityNotFoundException("Activity id can not delete");
		}

		this.activityRepository.deleteActivityById(id);
	}

	public ActivityViewModel getActivityViewModel(Long id) {
		Activity activity = this.activityRepository.findActivityById(id);
		ActivityViewModel activityViewModel = this.modelMapper.map(activity, ActivityViewModel.class);

		return activityViewModel;
	}
}
