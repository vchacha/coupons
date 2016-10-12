package logic.validation;

import core.database.model.CustomerDO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidator implements DataValidator<CustomerDO> {

	private static final int MINIMUM_ALLOWED_PASSWORD_LENGTH = 4;
	private static final long MINIMUN_NUMBER_ID = 1000;
	private static final int MINIMUN_ALLOWED_CUSTOMER_NAME_LENGTH = 2;

	@Override
	public ValidationResponse validateData(CustomerDO customerDO) {
		boolean isIdMeetRules = validateId(customerDO.getCustomerId());
		boolean isNameMeetRules = validateString(customerDO.getCustomerName(), MINIMUN_ALLOWED_CUSTOMER_NAME_LENGTH);
		boolean isPasswordMeetRules = validateString(customerDO.getPassword(), MINIMUM_ALLOWED_PASSWORD_LENGTH);
		boolean isEmailMeetRules = validateEmail(customerDO.getEmail());
		StringBuilder aggregateErrorMessage = new StringBuilder();
		if (!isIdMeetRules) {
			aggregateErrorMessage.append("ID does not meet required standards \n");
		}
		if (!isNameMeetRules) {
			aggregateErrorMessage.append("Your name does not meet required standards \n");
		}
		if (!isPasswordMeetRules) {
			aggregateErrorMessage.append("Password does not meet required standards \n");
		}
		if (!isEmailMeetRules) {
			aggregateErrorMessage.append("Email does not meet required standards \n");
		}
		if (!isIdMeetRules || !isNameMeetRules || !isPasswordMeetRules || !isEmailMeetRules) {
			return new ValidationResponse(false, aggregateErrorMessage.toString());
		}
		return new ValidationResponse(true, null);
	}

	private boolean validateId(long id) {
		if (id >= MINIMUN_NUMBER_ID) {
			return true;
		}
		return false;
	}

	private boolean validateString(String string, int minAllowedLength) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return string.length() >= minAllowedLength;
	}

	private boolean validateEmail(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&�*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&�*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

}
