package logic.exceptions;

public class CustomerValidationException extends RuntimeException {

	private static final long serialVersionUID = 6479997524885282390L;

	public CustomerValidationException(String errorMessage) {
		super(errorMessage);
	}

}
