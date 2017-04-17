package bg.car_wash.areas.customer.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Customer not update in database!")
public class CustomerNotUpdateException extends CarWashErrorException {
	public CustomerNotUpdateException(String message) {
		super(message);
	}
}
