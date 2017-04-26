package bg.car_wash.areas.activity.service;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.exceptions.ActivityDBEmptyException;
import bg.car_wash.areas.activity.exceptions.ActivityNotCreateException;
import bg.car_wash.areas.activity.exceptions.ActivityNotFoundException;
import bg.car_wash.areas.activity.exceptions.ActivityNotUpdateException;

import java.util.List;

public interface ActivityService {
	void createActivity(Activity activity) throws ActivityNotCreateException;

	List<Activity> findAllActivities() throws ActivityDBEmptyException;

	Activity findActivityByName(String name) throws ActivityNotFoundException;

	Activity findActivityById(Long id) throws ActivityNotFoundException;

	void updateActivity(Activity activity) throws ActivityNotUpdateException;

	void deleteActivityById(Long id) throws ActivityNotFoundException;
}
