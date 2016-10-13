package tdd.vendingMachine.util;

import java.util.Vector;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tdd.vendingMachine.impl.exceptions.IllegalEmptyArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalOutOfRangeArgumentException;
import tdd.vendingMachine.impl.util.Util;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.JVM)
public class UtilTest {

    private String intNumber = "10";

    private String nullNumber = null;
    private String nullArg = "nullNumber";

    private int number = - 5;
    private String arg = "number";

    private String emptyNumber = "";
    private String emptyArg = "emptyNumber";

    private Vector<Integer> emptyVector = new Vector<Integer>();
    private String emptyVectorArg = "emptyVector";

    @Test
    public final void testIsInteger() {
        assertTrue(Util.isInteger(intNumber));
    }

    @Test(expected = IllegalNullArgumentException.class)
    public final void testCheckNotNull() {
        Util.checkNotNull(nullNumber, nullArg);
    }

    @Test(expected = IllegalOutOfRangeArgumentException.class)
    public final void testCheckIsPositive() {
        Util.checkIsPositive(number, arg);
    }

    @Test(expected = IllegalEmptyArgumentException.class)
    public final void testCheckIsEmptyString() {
        Util.checkIsEmpty(emptyNumber, emptyArg);
    }

    @Test(expected = IllegalEmptyArgumentException.class)
    public final void testCheckIsEmptyVector() {
        Util.checkIsEmpty(emptyVector, emptyVectorArg);
    }
}