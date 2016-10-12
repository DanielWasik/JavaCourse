package tdd.vendingMachine.impl.exceptions;

public class IllegalClassArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = -6720290975098588352L;

    public IllegalClassArgumentException () {

    }

    public IllegalClassArgumentException(String message) {
        super(message);
    }

    public IllegalClassArgumentException (Throwable cause) {
        super (cause);
    }

    public IllegalClassArgumentException (String message, Throwable cause) {
        super (message, cause);
    }
}