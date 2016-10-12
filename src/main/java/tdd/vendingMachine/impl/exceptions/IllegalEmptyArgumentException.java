package tdd.vendingMachine.impl.exceptions;

public class IllegalEmptyArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = 1823179971271873288L;

    public IllegalEmptyArgumentException () {

    }

    public IllegalEmptyArgumentException(String message) {
        super(message);
    }

    public IllegalEmptyArgumentException (Throwable cause) {
        super (cause);
    }

    public IllegalEmptyArgumentException (String message, Throwable cause) {
        super (message, cause);
    }
}