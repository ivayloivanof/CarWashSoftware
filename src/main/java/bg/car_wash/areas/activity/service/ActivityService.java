package bg.car_wash.areas.activity.service;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.exception.ActivityDBEmptyException;
import bg.car_wash.areas.activity.exception.ActivityNotCreateException;
import bg.car_wash.areas.activity.exception.ActivityNotFoundException;

import java.util.List;

public interface ActivityService {
	void createActivity(Activity activity) throws ActivityNotCreateException;

	List<Activity> findAllActivities() throws ActivityDBEmptyException;

	Activity findActivityByName(String name) throws ActivityNotFoundException;
}
