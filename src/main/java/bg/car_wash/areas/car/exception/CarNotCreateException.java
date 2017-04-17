package bg.car_wash.areas.car.exception;

import bg.car_wash.exception.CarWashErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Car not create")
public class CarNotCreateException extends CarWashErrorException {
	public CarNotCreateException(String message) {
		super(message);
	}
}
