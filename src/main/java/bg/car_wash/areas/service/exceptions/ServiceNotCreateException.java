package bg.car_wash.areas.service.exceptions;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Service not create!")
public class ServiceNotCreateException extends CarWashErrorException {
	public ServiceNotCreateException(String message) {
		super(message);
	}
}
