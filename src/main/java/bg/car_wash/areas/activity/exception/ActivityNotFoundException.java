package bg.car_wash.areas.activity.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Activity not found in database")
public class ActivityNotFoundException extends CarWashErrorException {

	public ActivityNotFoundException(String message) {
		super(message);
	}
}