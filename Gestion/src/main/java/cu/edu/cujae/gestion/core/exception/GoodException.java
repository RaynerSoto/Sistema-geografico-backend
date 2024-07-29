package cu.edu.cujae.gestion.core.exception;

public class GoodException extends UnsupportedOperationException{
    public GoodException() {
    }

    public GoodException(String message) {
        super(message);
    }

    public GoodException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodException(Throwable cause) {
        super(cause);
    }
}
