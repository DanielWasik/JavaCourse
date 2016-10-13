package tdd.vendingMachine.messages;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tdd.vendingMachine.impl.messages.ERROR;
import tdd.vendingMachine.impl.messages.INFORMATION;
import tdd.vendingMachine.impl.messages.WARNING;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.JVM)
public class MessageTest {

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(INFORMATION.ALLOWED_COINS);
        assertNotNull(WARNING.NO_CHANGE);
        assertNotNull(ERROR.WRONG_INIT_CONFIG);
    }

    @Test
    public final void testInformationMessage() {
        String msg = "You can insert such coins: ";
        assertTrue(msg.compareTo(INFORMATION.ALLOWED_COINS.get()) == 0);
    }

    @Test
    public final void testWarningMessage() {
        String msg = "Sorry, there is no enough coins to give you a change. Please, get back your money.";
        assertTrue(msg.compareTo(WARNING.NO_CHANGE.get()) == 0);
    }

    @Test
    public final void testErrorMessage() {
        String msg = "Vending machine was not configured properly.";
        assertTrue(msg.compareTo(ERROR.WRONG_INIT_CONFIG.get()) == 0);
    }
}