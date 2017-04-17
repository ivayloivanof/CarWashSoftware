package bg.car_wash.areas.user.exception;

import bg.car_wash.configurations.error.Errors;
import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = Errors.NOT_FOUND_USER)
public class UserNotFoundException extends CarWashErrorException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
