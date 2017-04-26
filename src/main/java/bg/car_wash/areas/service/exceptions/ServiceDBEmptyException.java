package bg.car_wash.areas.service.exceptions;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Service database is empty!")
public class ServiceDBEmptyException extends CarWashErrorException {
	public ServiceDBEmptyException(String message) {
		super(message);
	}
}
