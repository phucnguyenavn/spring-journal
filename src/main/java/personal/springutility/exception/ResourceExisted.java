package personal.springutility.exception;

public class ResourceExisted extends RuntimeException {
    public ResourceExisted(String message) {
        super(message);
    }
}
