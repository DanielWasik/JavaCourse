package tdd.vendingMachine.messages;

import java.util.Arrays;
import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class MessageTest {

    public MESSAGE_ENUM_DUMMY messageType;

    public MessageTest(MESSAGE_ENUM_DUMMY messageType) {
        this.messageType = messageType;
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(messageType);
    }

    @Test
    public final void testInformationMessage() {
        String msg = "You can insert such coins: ";
        assertTrue(msg.compareTo(messageType.get()) == 0);
    }

    @Test
    public final void testWarningMessage() {
        String msg = "Sorry, there is no enough coins to give you a change. Please, get back your money.";
        assertTrue(msg.compareTo(messageType.get()) == 0);
    }

    @Test
    public final void testErrorMessage() {
        String msg = "Vending machine was not configured properly.";
        assertTrue(msg.compareTo(messageType.get()) == 0);
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[]{
                                            new MESSAGE_ENUM_DUMMY()
                                            });
    }
}

class MESSAGE_ENUM_DUMMY {
    static private String msg = "";

    public String get() {
        return msg;
    }
}