package personal.springutility.exception;

public class AlreadyExisted extends RuntimeException {
    public AlreadyExisted(String message) {
        super(message);
    }
}
