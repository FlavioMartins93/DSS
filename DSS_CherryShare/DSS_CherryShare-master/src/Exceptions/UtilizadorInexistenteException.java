package Exceptions;

public class UtilizadorInexistenteException extends Exception{
    public UtilizadorInexistenteException() {
        super();
    }

    public UtilizadorInexistenteException(String s) {
        super(s);
    }
}
