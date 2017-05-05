package bg.car_wash.exception;

import bg.car_wash.areas.activity.exceptions.ActivityDBEmptyException;
import bg.car_wash.areas.activity.exceptions.ActivityNotCreateException;
import bg.car_wash.areas.activity.exceptions.ActivityNotFoundException;
import bg.car_wash.areas.activity.exceptions.ActivityNotUpdateException;
import bg.car_wash.areas.car.exceptions.CarDBEmptyException;
import bg.car_wash.areas.car.exceptions.CarNotCreateException;
import bg.car_wash.areas.car.exceptions.CarNotFoundException;
import bg.car_wash.areas.customer.exceptions.CustomerDBEmptyException;
import bg.car_wash.areas.customer.exceptions.CustomerNotCreateException;
import bg.car_wash.areas.customer.exceptions.CustomerNotFoundException;
import bg.car_wash.areas.customer.exceptions.CustomerNotUpdateException;
import bg.car_wash.areas.service.exceptions.ServiceDBEmptyException;
import bg.car_wash.areas.service.exceptions.ServiceNotCreateException;
import bg.car_wash.areas.service.exceptions.ServiceNotFoundException;
import bg.car_wash.areas.service.exceptions.ServiceNotUpdateException;
import bg.car_wash.areas.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({
			CarDBEmptyException.class,
			CarNotFoundException.class,
			ActivityDBEmptyException.class,
			ActivityNotFoundException.class,
			ActivityNotUpdateException.class,
			CustomerDBEmptyException.class,
			CustomerNotFoundException.class,
			CustomerNotUpdateException.class,
			ServiceDBEmptyException.class,
			ServiceNotFoundException.class,
			ServiceNotUpdateException.class,
			UserNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(Model model, CarWashErrorException exception) {
		model.addAttribute("message", exception.getMessage());

		return "error/404";
	}

	@ExceptionHandler({
			CarNotCreateException.class,
			ActivityNotCreateException.class,
			CustomerNotCreateException.class,
			ServiceNotCreateException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handle409(Model model, CarWashErrorException exception) {
		model.addAttribute("message", exception.getMessage());

		return "error/409";
	}
}
