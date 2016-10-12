package logic.exceptions;

public class DAOException extends RuntimeException {
	
	private static final long serialVersionUID = 5551520704492009506L;

	public DAOException(String errorMessage) {
		super(errorMessage);
	}

}
