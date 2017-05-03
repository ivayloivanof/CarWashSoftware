package bg.car_wash.areas.customer.annotation.validation;

import bg.car_wash.areas.customer.annotation.ValidCustomerPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerPhoneNumberValidator implements ConstraintValidator<ValidCustomerPhoneNumber, String> {

	private static final String PHONE_NUMBER_PATTERN = "^(\\+{0,1}[0-9]{1,3}\\s{0,1}[0-9]{3}\\s{0,1}[0-9]{3}\\s{0,1}[0-9]{3})$";
	private Pattern pattern;
	private Matcher matcher;

	@Override
	public void initialize(ValidCustomerPhoneNumber validCustomerPhoneNumber) {

	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext ctx) {
		if(phoneNumber == null) {
			return true;
		}

		return validatePhoneNumber(phoneNumber);
	}

	private boolean validatePhoneNumber(String phoneNumber) {
		pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
		matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}
}