package bg.car_wash.areas.activity.exceptions;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Activity can not update!")
public class ActivityNotUpdateException extends CarWashErrorException {

	public ActivityNotUpdateException(String message) {
		super(message);
	}
}
