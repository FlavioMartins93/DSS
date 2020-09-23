package Exceptions;

public class PasswordInvalidaException extends Exception{
    public PasswordInvalidaException() {
        super();
    }

    public PasswordInvalidaException(String s) {
        super(s);
    }
}