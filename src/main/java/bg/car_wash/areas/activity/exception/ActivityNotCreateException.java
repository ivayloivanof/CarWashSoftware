package bg.car_wash.areas.activity.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.CONFLICT, reason="Activity is not create!")
public class ActivityNotCreateException extends CarWashErrorException {

	public ActivityNotCreateException(String message) {
		super(message);
	}
}
