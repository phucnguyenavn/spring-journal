package personal.springutility.exception;

public class ServerError extends RuntimeException {
    public ServerError(String message) {
        super(message);
    }
}
