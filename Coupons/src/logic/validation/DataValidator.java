package logic.validation;

public interface DataValidator<T> {

	ValidationResponse validateData(T data);

}
