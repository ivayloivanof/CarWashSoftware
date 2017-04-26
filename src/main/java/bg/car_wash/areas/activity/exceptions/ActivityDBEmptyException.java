package bg.car_wash.areas.activity.exceptions;


import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Activity database is empty")
public class ActivityDBEmptyException extends CarWashErrorException {

	public ActivityDBEmptyException(String message) {
		super(message);
	}
}
