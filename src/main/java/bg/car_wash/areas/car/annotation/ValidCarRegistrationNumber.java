package bg.car_wash.areas.car.annotation;

import bg.car_wash.areas.car.annotation.validation.CarRegistrationNumberValidator;

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
@Constraint(validatedBy = CarRegistrationNumberValidator.class)
public @interface ValidCarRegistrationNumber {
	String message() default "This car registration number is not valid!\nRegistration number like: 'CA3465HT'!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
