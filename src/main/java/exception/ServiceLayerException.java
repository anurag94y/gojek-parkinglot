package exception;

public class ServiceLayerException extends Exception{
    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLayerException(Exception exception) {
        super(exception);
    }
}
