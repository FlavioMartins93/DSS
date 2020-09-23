package Exceptions;

public class FicheiroDuplicadoException extends Exception{
    public FicheiroDuplicadoException() {
        super();
    }

    public FicheiroDuplicadoException(String s) {
        super(s);
    }
}