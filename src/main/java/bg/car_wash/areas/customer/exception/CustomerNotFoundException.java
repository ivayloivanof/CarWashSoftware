package bg.car_wash.areas.customer.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer not found in database!")
public class CustomerNotFoundException extends CarWashErrorException {
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
