package exception;

public class DataLayerException extends Exception {

    public DataLayerException(String message) {
        super(message);
    }

    public DataLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataLayerException(Exception exception) {
        super(exception);
    }

}
