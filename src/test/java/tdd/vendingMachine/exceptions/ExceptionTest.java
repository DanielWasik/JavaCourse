package tdd.vendingMachine.exceptions;

import org.junit.Test;

import tdd.vendingMachine.impl.exceptions.*;
import static org.junit.Assert.assertEquals;

public class ExceptionTest {

    String message = "This is expected exception";
    IllegalEmptyArgumentException emptyEx = new IllegalEmptyArgumentException(message);
    IllegalNullArgumentException nullEx = new IllegalNullArgumentException(message);
    IllegalOutOfRangeArgumentException outOfRangeEx =  new IllegalOutOfRangeArgumentException(message);
    IllegalClassArgumentException classEx =  new IllegalClassArgumentException(message);

    @Test(expected = IllegalArgumentException.class)
    public void testThrowIllegalArgumentException() {
        throw emptyEx;
    }

    @Test(expected = IllegalEmptyArgumentException.class)
    public void testThrowIllegalEmptyArgumentException() {
        throw emptyEx;
    }

    @Test(expected = IllegalNullArgumentException.class)
    public void testThrowIllegalNullArgumentException() {
        throw nullEx;
    }

    @Test(expected = IllegalOutOfRangeArgumentException.class)
    public void testThrowIllegalOutOfRangeArgumentException() {
        throw outOfRangeEx;
    }

    @Test(expected = IllegalClassArgumentException.class)
    public void testThrowIllegalClassArgumentException() {
        throw classEx;
    }

    @Test
    public void testExeptionMessage() {
        String message = "This is expected exception";

        try {
            throw outOfRangeEx;
        }
        catch (IllegalOutOfRangeArgumentException ex)
        {
            assertEquals(message, ex.getMessage());
        }
    }
}