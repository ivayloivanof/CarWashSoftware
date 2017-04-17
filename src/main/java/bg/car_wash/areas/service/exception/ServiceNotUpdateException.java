package bg.car_wash.areas.service.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Service can not update in database!")
public class ServiceNotUpdateException extends CarWashErrorException {
	public ServiceNotUpdateException(String message) {
		super(message);
	}
}
