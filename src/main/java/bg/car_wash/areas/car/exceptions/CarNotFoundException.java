package bg.car_wash.areas.car.exceptions;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Car not found in database!")
public class CarNotFoundException extends CarWashErrorException {
	public CarNotFoundException(String message) {
		super(message);
	}
}
