package tdd.vendingMachine.machine;

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
public class CoinBoxTest {

    @SuppressWarnings("rawtypes")
    private Class interfaceImpl;
    private Object obj;

    @SuppressWarnings("rawtypes")
    private Class noparams[] = {};

    @SuppressWarnings("rawtypes")
    private Class[] paramCoin = new Class[1];

    @SuppressWarnings("rawtypes")
    private Class[] paramVCoins = new Class[1];

    @SuppressWarnings("rawtypes")
    private Class[] paramInt = new Class[1];

    @SuppressWarnings("rawtypes")
    private Class[] paramBigDecimal = new Class[1];

    private Class<?>[] args = new Class<?>[2];

    private Method addCoinFunc;
    private Method removeCoinsFunc;
    private Method saveChangeFunc;
    private Method resetChangeFunc;
    private Method giveChangeFunc;
    private Method isChangePossibleFunc;

    private Coin coin1, coin2, coin3;

    private Vector<Coin> m_coins;

    @SuppressWarnings("rawtypes")
    public CoinBoxTest(Class interfaceImpl) {
        this.interfaceImpl = interfaceImpl;
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {
        obj = interfaceImpl.newInstance();
        paramCoin[0] = Coin.class;
        paramVCoins[0] = Vector.class;
        paramInt[0] = Integer.class;
        paramBigDecimal[0] = BigDecimal.class;
        args[0] = Vector.class;
        args[1] = int.class;

        coin1 = new Coin(COIN_TYPE.FIVE_DOLAR);
        coin2 = new Coin(COIN_TYPE.ONE_DOLAR);
        coin3 = new Coin(COIN_TYPE.TEN_CENT);

        m_coins = new Vector<Coin>();
        m_coins.add(coin1);
        m_coins.add(coin2);
        m_coins.add(coin3);
        
        addCoinFunc = interfaceImpl.getDeclaredMethod("addCoin", paramCoin);
        removeCoinsFunc = interfaceImpl.getDeclaredMethod("removeCoins", paramVCoins);
        saveChangeFunc = interfaceImpl.getDeclaredMethod("saveChange", args);
        resetChangeFunc = interfaceImpl.getDeclaredMethod("resetChange", noparams);
        giveChangeFunc = interfaceImpl.getDeclaredMethod("giveChange", noparams);
        isChangePossibleFunc = interfaceImpl.getDeclaredMethod("isChangePossible", paramBigDecimal);

        addCoinFunc.setAccessible(true);
        removeCoinsFunc.setAccessible(true);
        saveChangeFunc.setAccessible(true);
        resetChangeFunc.setAccessible(true);
        giveChangeFunc.setAccessible(true);
        isChangePossibleFunc.setAccessible(true);
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(interfaceImpl);
    }

    @Test
    public final void testAddCoin() {
        try
        {
            Object value1 = addCoinFunc.invoke(obj, coin1);
            boolean success = (boolean) value1;

            assertTrue(success);

            Object value2 = addCoinFunc.invoke(obj, coin2);
            success = (boolean) value2;

            assertTrue(success);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testRemoveCoins() {
        try
        {
            Object value1 = addCoinFunc.invoke(obj, coin1);
            Object value2 = addCoinFunc.invoke(obj, coin2);
            Object value3 = addCoinFunc.invoke(obj, coin3);

            boolean success = (boolean) value1 && (boolean) value2 && (boolean) value3;

            Object value4 = removeCoinsFunc.invoke(obj, m_coins);
            success = (boolean) value4;

            assertTrue(success);

            Coin coin4 = new Coin(COIN_TYPE.FIFTY_CENT);
            m_coins.clear();
            m_coins.add(coin4);

            Object value5 = removeCoinsFunc.invoke(obj, m_coins);
            success = (boolean) value5;

            assertFalse(success);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testSaveChange() {
        try
        {
            saveChangeFunc.invoke(obj, m_coins, m_coins.size());

            Object value1 = giveChangeFunc.invoke(obj, (Object[]) null);
            @SuppressWarnings("unchecked")
            Vector<Coin> change = (Vector<Coin>) value1;

            assertNotNull(change);
            assertEquals(m_coins.size(), change.size());
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testResetChange() {
        try
        {
            saveChangeFunc.invoke(obj, m_coins, m_coins.size());

            Object value1 = giveChangeFunc.invoke(obj, (Object[]) null);
            @SuppressWarnings("unchecked")
            Vector<Coin> change = (Vector<Coin>) value1;

            assertNotNull(change);
            assertEquals(m_coins.size(), change.size());

            resetChangeFunc.invoke(obj, (Object[]) null);

            Object value2 = giveChangeFunc.invoke(obj, (Object[]) null);
            @SuppressWarnings("unchecked")
            Vector<Coin> changeAfterReset = (Vector<Coin>) value2;

            assertNotNull(change);
            assertEquals(0, changeAfterReset.size());
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGiveChange() {
        try
        {
            saveChangeFunc.invoke(obj, m_coins, m_coins.size());

            Object value1 = giveChangeFunc.invoke(obj, (Object[]) null);
            @SuppressWarnings("unchecked")
            Vector<Coin> change = (Vector<Coin>) value1;

            assertNotNull(change);
            assertEquals(m_coins.size(), change.size());
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }


    @Test
    public final void testIsChangePossible() {
        try
        {
            addCoinFunc.invoke(obj, m_coins.firstElement());

            Object value1 = isChangePossibleFunc.invoke(obj, m_coins.firstElement().getValue());
            boolean changePossible = (boolean) value1;

            assertTrue(changePossible);

            removeCoinsFunc.invoke(obj, m_coins);

            Object value2 = isChangePossibleFunc.invoke(obj, m_coins.firstElement().getValue());
            changePossible = (boolean) value2;

            assertFalse(changePossible);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @After
    public void tearDown() {

        Vector<Coin> coins = new Vector<Coin>();
        coins.add(coin1);
        coins.add(coin2);
        coins.add(coin3);

        try {
            removeCoinsFunc.invoke(obj, m_coins);
            removeCoinsFunc.invoke(obj, coins);
            m_coins.clear();
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() throws ClassNotFoundException {
        return Arrays.asList(new Object[]{
                                            Class.forName("tdd.vendingMachine.impl.machine.CoinBox")
                                            });
    }
}