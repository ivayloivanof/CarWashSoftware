package bg.car_wash.areas.activity.service;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.exceptions.ActivityDBEmptyException;
import bg.car_wash.areas.activity.exceptions.ActivityNotCreateException;
import bg.car_wash.areas.activity.exceptions.ActivityNotFoundException;
import bg.car_wash.areas.activity.exceptions.ActivityNotUpdateException;
import bg.car_wash.areas.activity.models.bindingModel.ActivityBindingModel;
import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;

import java.util.List;

public interface ActivityService {
	void createActivity(ActivityBindingModel activityBindingModel) throws ActivityNotCreateException;

	List<ActivityViewModel> findAllActivities() throws ActivityDBEmptyException;

	Activity findActivityByName(String name) throws ActivityNotFoundException;

	Activity findActivityById(Long id) throws ActivityNotFoundException;

	void updateActivity(ActivityBindingModel activityBindingModel) throws ActivityNotUpdateException;

	void deleteActivityById(Long id) throws ActivityNotFoundException;

	ActivityViewModel getActivityViewModel(Long id);
}
