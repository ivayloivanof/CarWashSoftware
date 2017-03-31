package bg.car_wash.areas.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Not foud user")
public class UserNotFoundException extends RuntimeException {
}
