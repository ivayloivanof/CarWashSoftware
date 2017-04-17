package bg.car_wash.areas.service.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Service not found in database!")
public class ServiceNotFoundException extends CarWashErrorException {
	public ServiceNotFoundException(String message) {
		super(message);
	}
}
