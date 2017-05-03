package bg.car_wash.areas.car.annotation.validation;

import bg.car_wash.areas.car.annotation.ValidCarRegistrationNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarRegistrationNumberValidator implements ConstraintValidator<ValidCarRegistrationNumber, String> {

	private static final String REGISTRATION_NUMBER_PATTERN = "^([a-zA-z]{1,2})([0-9]{4})([a-zA-z]{1,2})$";
	private Pattern pattern;
	private Matcher matcher;

	@Override
	public void initialize(ValidCarRegistrationNumber validCarRegistrationNumber) {

	}

	@Override
	public boolean isValid(String registrationNumber, ConstraintValidatorContext ctx) {
		if(registrationNumber == null) {
			return true;
		}

		return validateRegistrationNumber(registrationNumber);
	}

	private boolean validateRegistrationNumber(String registrationNumber) {
		pattern = Pattern.compile(REGISTRATION_NUMBER_PATTERN);
		matcher = pattern.matcher(registrationNumber);
		return matcher.matches();
	}
}
