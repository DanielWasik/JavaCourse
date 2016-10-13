package tdd.vendingMachine.coin;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import tdd.vendingMachine.impl.coin.COIN_TYPE;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class CoinTypeTest {

    public COIN_TYPE coinType;

    private BigDecimal coinTypeValue5 = new BigDecimal("5");
    private BigDecimal coinTypeValue2 = new BigDecimal("2");

    private String coinTypeStrValue5 = "5$";
    private String coinTypeStrValue2 = "2$";

    private String stringAll = "5$, 2$, 1$, 0.5$, 0.2$, 0.1$";

    public CoinTypeTest(COIN_TYPE coinType) {
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
        assertTrue(COIN_TYPE.hasValue(coinTypeStrValue2));
    }

    @Test
    public final void testGetAll() {
        assertTrue(stringAll.compareTo(COIN_TYPE.getAll()) == 0);
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[]{
                                            COIN_TYPE.FIVE_DOLAR
                                            });
    }
}