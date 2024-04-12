package exceptions;

public class PlatformNotSupportException extends RuntimeException{
    public PlatformNotSupportException(String message) {
        super(message);
    }
}
