package Vmo.Springpro.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CenterNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public CenterNotFoundException(int id) {
        super("Center not found with ID: " + id);
    }

    public CenterNotFoundException(String message) {
        super(message);
    }

    public CenterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
