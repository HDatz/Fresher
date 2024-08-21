package Vmo.Springpro.Error;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private final ErrorClass errorClass;

    public AppException(ErrorClass errorClass) {
        super(errorClass.getMessage());
        this.errorClass = errorClass;
    }
}
