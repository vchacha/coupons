package logic.exceptions;

public class CompanyValidationException extends RuntimeException {

	private static final long serialVersionUID = -8540284985630368455L;

	public CompanyValidationException(String errorMessage) {
		super(errorMessage);
	}

}
