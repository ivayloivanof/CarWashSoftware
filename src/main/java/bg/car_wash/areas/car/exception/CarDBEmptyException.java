package bg.car_wash.areas.car.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Car database is empty")
public class CarDBEmptyException extends CarWashErrorException {
	public CarDBEmptyException(String message) {
		super(message);
	}
}
