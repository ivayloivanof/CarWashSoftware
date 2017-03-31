package bg.car_wash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleGlobalException() {

		//TODO create and return page for all other global exception
		return "";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public String handle404() {
		//TODO create and return 404 page for not found exception
		return "exception/404";
	}
}
