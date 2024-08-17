package Vmo.Springpro.Error;

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
