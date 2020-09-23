package Exceptions;

public class MusicaInexistenteException extends Exception{
    public MusicaInexistenteException() {
        super();
    }

    public MusicaInexistenteException(String s) {
        super(s);
    }
}