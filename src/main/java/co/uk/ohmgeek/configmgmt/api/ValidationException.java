package co.uk.ohmgeek.configmgmt.api;

public class ValidationException extends Exception{
    public ValidationException(String msg, Exception e) {
        super(msg, e);
    }

    public ValidationException(String msg) {
        super(msg);
    }
}
