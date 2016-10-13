package tdd.vendingMachine.coin;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import tdd.vendingMachine.ICoin;
import tdd.vendingMachine.impl.coin.COIN_TYPE;
import tdd.vendingMachine.impl.coin.Coin;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class CoinTest {

    public ICoin interfaceImpl;

    private BigDecimal coinValueFive = new BigDecimal("5");
    private BigDecimal coinValueTwo = new BigDecimal("2");

    public CoinTest(ICoin interfaceImpl) {
        this.interfaceImpl = interfaceImpl;
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(interfaceImpl);
    }

    @Test
    public final void testGetValueEqual() {
        assertTrue(coinValueFive.compareTo(interfaceImpl.getValue()) == 0);
    }

    @Test
    public final void testGetValueNotEqual() {
        assertFalse(coinValueTwo.compareTo(interfaceImpl.getValue()) == 0);
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[]{
                                            Coin.createCoins(COIN_TYPE.FIVE_DOLAR, 1).firstElement()
                                            });
    }
}