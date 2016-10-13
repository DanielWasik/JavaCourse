package tdd.vendingMachine.software;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import tdd.vendingMachine.impl.coin.COIN_TYPE;
import tdd.vendingMachine.impl.coin.Coin;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class CreditSoftwareTest {

    @SuppressWarnings("rawtypes")
    private Class interfaceImpl;
    private Object obj;

    @SuppressWarnings("rawtypes")
    private Class noparams[] = {};

    @SuppressWarnings("rawtypes")
    private Class[] paramCoin = new Class[1];

    private Method isEmptyFunc;
    private Method getValueFunc;
    private Method getCreditCoinsFunc;
    private Method getValueAsStringFunc;
    private Method addCoinFunc;
    private Method resetFunc;
    private Method getCoinsFunc;

    private Coin coin1, coin2, coin3;

    @SuppressWarnings("rawtypes")
    public CreditSoftwareTest(Class interfaceImpl) {
        this.interfaceImpl = interfaceImpl;
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {
        obj = interfaceImpl.newInstance();
        paramCoin[0] = Coin.class;

        coin1 = new Coin(COIN_TYPE.FIVE_DOLAR);
        coin2 = new Coin(COIN_TYPE.ONE_DOLAR);
        coin3 = new Coin(COIN_TYPE.TEN_CENT);

        isEmptyFunc = interfaceImpl.getDeclaredMethod("isEmpty", noparams);
        getValueFunc = interfaceImpl.getDeclaredMethod("getValue", noparams);
        getCreditCoinsFunc = interfaceImpl.getDeclaredMethod("getCreditCoins", noparams);
        getValueAsStringFunc = interfaceImpl.getDeclaredMethod("getValueAsString", noparams);
        addCoinFunc = interfaceImpl.getDeclaredMethod("addCoin", paramCoin);
        resetFunc = interfaceImpl.getDeclaredMethod("reset", noparams);
        getCoinsFunc = interfaceImpl.getDeclaredMethod("getCoins", noparams);

        isEmptyFunc.setAccessible(true);
        getValueFunc.setAccessible(true);
        getCreditCoinsFunc.setAccessible(true);
        getValueAsStringFunc.setAccessible(true);
        addCoinFunc.setAccessible(true);
        resetFunc.setAccessible(true);
        getCoinsFunc.setAccessible(true);
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(interfaceImpl);
    }

    @Test
    public final void testIsEmpty() {
        try
        {
            Object value1 = isEmptyFunc.invoke(obj, (Object[]) null);
            boolean isEmpty = (boolean) value1;

            assertTrue(isEmpty);

            addCoinFunc.invoke(obj, coin1);

            Object value2 = isEmptyFunc.invoke(obj, (Object[]) null);
            isEmpty = (boolean) value2;

            assertFalse(isEmpty);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testReset() {
        try
        {
            Object value1 = isEmptyFunc.invoke(obj, (Object[]) null);
            boolean isEmpty = (boolean) value1;

            assertTrue(isEmpty);

            addCoinFunc.invoke(obj, coin1);

            Object value2 = isEmptyFunc.invoke(obj, (Object[]) null);
            isEmpty = (boolean) value2;

            assertFalse(isEmpty);

            resetFunc.invoke(obj, (Object[]) null);

            Object value3 = isEmptyFunc.invoke(obj, (Object[]) null);
            isEmpty = (boolean) value3;

            assertTrue(isEmpty);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testAddCoin() {
        try
        {
            addCoinFunc.invoke(obj, coin1);

            Object value1 = isEmptyFunc.invoke(obj, (Object[]) null);
            boolean isEmpty = (boolean) value1;

            assertFalse(isEmpty);

            Object value2 = getValueFunc.invoke(obj, (Object[]) null);
            BigDecimal coinValue = (BigDecimal) value2;

            assertTrue(coin1.getValue().compareTo(coinValue) == 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetValue() {
        try
        {
            addCoinFunc.invoke(obj, coin1);

            Object value1 = isEmptyFunc.invoke(obj, (Object[]) null);
            boolean isEmpty = (boolean) value1;

            assertFalse(isEmpty);

            Object value2 = getValueFunc.invoke(obj, (Object[]) null);
            BigDecimal coinValue = (BigDecimal) value2;

            assertTrue(coin1.getValue().compareTo(coinValue) == 0);

            addCoinFunc.invoke(obj, coin2);

            BigDecimal sum = coin1.getValue().add(coin2.getValue());

            Object value3 = getValueFunc.invoke(obj, (Object[]) null);
            BigDecimal sumValue = (BigDecimal) value3;

            assertTrue(sum.compareTo(sumValue) == 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetValueAsString() {
        try
        {
            addCoinFunc.invoke(obj, coin1);

            Object value1 = isEmptyFunc.invoke(obj, (Object[]) null);
            boolean isEmpty = (boolean) value1;

            assertFalse(isEmpty);

            Object value2 = getValueAsStringFunc.invoke(obj, (Object[]) null);
            String coinValue = (String) value2;

            assertTrue(coin1.getValue().setScale(1).toString().compareTo(coinValue) == 0);

            addCoinFunc.invoke(obj, coin2);

            BigDecimal sum = coin1.getValue().add(coin2.getValue());

            Object value3 = getValueAsStringFunc.invoke(obj, (Object[]) null);
            String sumValue = (String) value3;

            assertTrue(sum.setScale(1).toString().compareTo(sumValue) == 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }


    @Test
    public final void testGetCreditCoins() {
        try
        {
            addCoinFunc.invoke(obj, coin1);
            addCoinFunc.invoke(obj, coin2);
            addCoinFunc.invoke(obj, coin3);

            Object value1 = isEmptyFunc.invoke(obj, (Object[]) null);
            boolean isEmpty = (boolean) value1;

            assertFalse(isEmpty);

            Object value2 = getCreditCoinsFunc.invoke(obj, (Object[]) null);
            String creditCoins = (String) value2;

            String coins = coin1.getType().getStrValue() + ", " + coin2.getType().getStrValue() + ", " + coin3.getType().getStrValue();

            assertTrue(coins.compareTo(creditCoins) == 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetCoins() {
        try
        {
            addCoinFunc.invoke(obj, coin1);
            addCoinFunc.invoke(obj, coin2);
            addCoinFunc.invoke(obj, coin3);

            Object value1 = isEmptyFunc.invoke(obj, (Object[]) null);
            boolean isEmpty = (boolean) value1;

            assertFalse(isEmpty);

            Object value2 = getCoinsFunc.invoke(obj, (Object[]) null);
            @SuppressWarnings("unchecked")
            Vector<Coin> creditCoins = (Vector<Coin>) value2;

            assertTrue(creditCoins.size() == 3);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @After
    public void tearDown() {

        try {
            resetFunc.invoke(obj, (Object[]) null);

        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() throws ClassNotFoundException {
        return Arrays.asList(new Object[]{
                                            Class.forName("tdd.vendingMachine.impl.software.CreditSoftware")
                                            });
    }
}