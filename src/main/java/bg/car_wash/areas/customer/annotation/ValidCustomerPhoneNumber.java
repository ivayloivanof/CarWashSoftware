package bg.car_wash.areas.customer.annotation;

import bg.car_wash.areas.customer.annotation.validation.CustomerPhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = CustomerPhoneNumberValidator.class)
public @interface ValidCustomerPhoneNumber {
	String message() default "This phone number is not valid! Phone number like: '+359 111 222 333'!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
