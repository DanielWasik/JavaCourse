package tdd.vendingMachine.impl.exceptions;

public class IllegalNullArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = -3122725848750425144L;

    public IllegalNullArgumentException () {

    }

    public IllegalNullArgumentException(String message) {
        super(message);
    }

    public IllegalNullArgumentException (Throwable cause) {
        super (cause);
    }

    public IllegalNullArgumentException (String message, Throwable cause) {
        super (message, cause);
    }
}