package logic.exceptions;

public class CouponValidationException extends RuntimeException {

	private static final long serialVersionUID = -1363422396636932603L;

	public CouponValidationException(String errorMessage) {
		super(errorMessage);
	}

}
