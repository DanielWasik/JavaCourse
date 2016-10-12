package tdd.vendingMachine.impl.exceptions;

public class IllegalOutOfRangeArgumentException extends IllegalArgumentException {

    private static final long serialVersionUID = 8476705709867184938L;

    public IllegalOutOfRangeArgumentException () {

    }

    public IllegalOutOfRangeArgumentException(String message) {
        super(message);
    }

    public IllegalOutOfRangeArgumentException (Throwable cause) {
        super (cause);
    }

    public IllegalOutOfRangeArgumentException (String message, Throwable cause) {
        super (message, cause);
    }
}