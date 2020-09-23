package Exceptions;

public class NotMusicOwnerException extends Exception{
    public NotMusicOwnerException() {
        super();
    }

    public NotMusicOwnerException(String s) {
        super(s);
    }
}
