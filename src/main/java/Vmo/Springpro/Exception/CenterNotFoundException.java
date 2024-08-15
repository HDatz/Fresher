package Vmo.Springpro.Exception;

public class CenterNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public CenterNotFoundException(int id) {
        super("Center with ID " + id + " not found.");
    }
}
