package tdd.vendingMachine.display;

import java.util.Arrays;
import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import tdd.vendingMachine.IDisplay;
import tdd.vendingMachine.impl.display.Display;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class DisplayTest {

    private IDisplay interfaceImpl;
    String msg = "Test message";

    public DisplayTest(IDisplay interfaceImpl) {
        this.interfaceImpl = interfaceImpl;
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(interfaceImpl);
    }

    @Test
    public final void testDisplayMessage() {
        boolean result = true;

        try {
            interfaceImpl.displayMessage(msg);
        } catch (Exception ex) {
            result = false;
        }

        assertTrue(result);
    }

    @Test
    public final void testDisplayInputMessage() {
        boolean result = true;

        try {
            interfaceImpl.displayInputMessage(msg);
        } catch (Exception ex) {
            result = false;
        }

        assertTrue(result);
    }

    @Test
    public final void testClearScreen() {
        boolean result = true;

        try {
            interfaceImpl.clearScreen();
        } catch (Exception ex) {
            result = false;
        }

        assertTrue(result);
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[] { new Display() });
    }
}