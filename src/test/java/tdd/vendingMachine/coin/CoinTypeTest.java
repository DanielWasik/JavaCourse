package tdd.vendingMachine.coin;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class CoinTypeTest {

    public COIN_TYPE_ENUM_DUMMY coinType;

    private BigDecimal coinTypeValue5 = new BigDecimal("5");
    private BigDecimal coinTypeValue2 = new BigDecimal("2");

    private String coinTypeStrValue5 = "5$";
    private String coinTypeStrValue2 = "2$";

    private String stringAll = "5$, 2$, 1$, 0.5$, 0.2$, 0.1$";

    public CoinTypeTest(COIN_TYPE_ENUM_DUMMY coinType) {
        this.coinType = coinType;
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(coinType);
    }

    @Test
    public final void testGetValueEqual() {
        assertTrue(coinTypeValue5.compareTo(coinType.getValue()) == 0);
    }

    @Test
    public final void testGetValueNotEqual() {
        assertFalse(coinTypeValue2.compareTo(coinType.getValue()) == 0);
    }

    @Test
    public final void testGetStrValue() {
        assertTrue(coinTypeStrValue5.compareTo(coinType.getStrValue()) == 0);
    }

    @Test
    public final void testHasValue() {
        assertTrue(coinType.hasValue(coinTypeStrValue2));
    }

    @Test
    public final void testGetAll() {
        assertTrue(stringAll.compareTo(coinType.getAll()) == 0);
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[]{
                                            new COIN_TYPE_ENUM_DUMMY()
                                            });
    }
}

class COIN_TYPE_ENUM_DUMMY {
    static private Vector<BigDecimal> coinTypeValues = new Vector<BigDecimal>(0);
    static private Vector<String> coinTypeStrValues = new Vector<String>(0);

    public BigDecimal getValue() {
        return coinTypeValues.get(0);
    }

    public String getStrValue() {
        return coinTypeStrValues.get(0);
    }

    public boolean hasValue(String coinType) {
        return false;
    }

    public String getAll() {
        String result = "";
        Enumeration<String> e = coinTypeStrValues.elements();
        
        while(e.hasMoreElements())
        {
            result += e.nextElement() + ", ";
        }

        return (result.isEmpty() ? result : result.substring(0, result.length() - 2));
    }

    static {
        coinTypeValues.addElement(new BigDecimal("50"));
        coinTypeValues.addElement(new BigDecimal("20"));
        coinTypeValues.addElement(new BigDecimal("10"));
        coinTypeValues.addElement(new BigDecimal("0.05"));
        coinTypeValues.addElement(new BigDecimal("0.02"));
        coinTypeValues.addElement(new BigDecimal("0.01"));

        coinTypeStrValues.addElement("50$");
        coinTypeStrValues.addElement("20$");
        coinTypeStrValues.addElement("10$");
        coinTypeStrValues.addElement("0.50$");
        coinTypeStrValues.addElement("0.20$");
        coinTypeStrValues.addElement("0.10$");
    }
}