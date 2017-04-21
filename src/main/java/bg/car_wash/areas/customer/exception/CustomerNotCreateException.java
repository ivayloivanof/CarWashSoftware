package bg.car_wash.areas.customer.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Customer not create!")
public class CustomerNotCreateException extends CarWashErrorException {
	public CustomerNotCreateException(String message) {
		super(message);
	}
}
