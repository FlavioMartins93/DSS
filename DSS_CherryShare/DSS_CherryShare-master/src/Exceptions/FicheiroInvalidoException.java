package Exceptions;

public class FicheiroInvalidoException extends Exception{
    public FicheiroInvalidoException() {
        super();
    }

    public FicheiroInvalidoException(String s) {
        super(s);
    }
}