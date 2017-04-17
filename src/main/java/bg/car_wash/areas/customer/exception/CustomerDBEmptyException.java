package bg.car_wash.areas.customer.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Customer database is empty!")
public class CustomerDBEmptyException extends CarWashErrorException {

	public CustomerDBEmptyException(String message) {
		super(message);
	}
}
