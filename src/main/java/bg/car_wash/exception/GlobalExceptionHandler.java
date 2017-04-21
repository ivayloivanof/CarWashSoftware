package bg.car_wash.exception;

import bg.car_wash.areas.activity.exception.ActivityDBEmptyException;
import bg.car_wash.areas.activity.exception.ActivityNotCreateException;
import bg.car_wash.areas.activity.exception.ActivityNotFoundException;
import bg.car_wash.areas.activity.exception.ActivityNotUpdateException;
import bg.car_wash.areas.car.exception.CarDBEmptyException;
import bg.car_wash.areas.car.exception.CarNotCreateException;
import bg.car_wash.areas.car.exception.CarNotFoundException;
import bg.car_wash.areas.customer.exception.CustomerDBEmptyException;
import bg.car_wash.areas.customer.exception.CustomerNotCreateException;
import bg.car_wash.areas.customer.exception.CustomerNotFoundException;
import bg.car_wash.areas.customer.exception.CustomerNotUpdateException;
import bg.car_wash.areas.service.exception.ServiceDBEmptyException;
import bg.car_wash.areas.service.exception.ServiceNotCreateException;
import bg.car_wash.areas.service.exception.ServiceNotFoundException;
import bg.car_wash.areas.service.exception.ServiceNotUpdateException;
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
