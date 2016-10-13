package tdd.vendingMachine.software;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import tdd.vendingMachine.IProduct;
import tdd.vendingMachine.IShelve;
import tdd.vendingMachine.IVendingMachine;
import tdd.vendingMachine.PRODUCT_TYPE;
import tdd.vendingMachine.impl.coin.COIN_TYPE;
import tdd.vendingMachine.impl.coin.Coin;
import tdd.vendingMachine.impl.machine.VendingMachine;
import tdd.vendingMachine.impl.product.Product;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class MachineSoftwareTest {

    private VendingMachine m_machine;
    private int m_nbrOfShelves;

    private Class<?>[] argTypes = {IVendingMachine.class};
    private Class<?> interfaceImpl;
    private Constructor<?> cons;
    private Object obj;

    @SuppressWarnings("rawtypes")
    private Class noparams[] = {};

    @SuppressWarnings("rawtypes")
    private Class[] paramString = new Class[1];

    @SuppressWarnings("rawtypes")
    private Class[] paramInt = new Class[1];

    @SuppressWarnings("rawtypes")
    private Class[] paramBool = new Class[1];

    private Vector<Product> m_bars;
    private Vector<Product> m_cola;
    private Vector<Product> m_juice;

    private Vector<Coin> m_coins;

    private int shelveNrb1;
    private int shelveNrb2;
    private int shelveNrb3;

    private Method setSoldOutFunc;
    private Method getSoldOutFunc;
    private Method getShelvesNbrFunc;
    private Method getCreditAsStringFunc;
    private Method getCreditCoinsFunc;
    private Method isCreditEmptyFunc;
    private Method resetCreditFunc;
    private Method cancelTransactionFunc;
    private Method getAddMoneyAsStringFunc;
    private Method isGiveChangeNeededFunc;
    private Method isAddMoneyNeededFunc;
    private Method setCurrPriceFunc;
    private Method isShelveNbrValidFunc;
    private Method isCoinValidFunc;
    private Method isGiveChangePossibleFunc;
    private Method giveChangeFunc;
    private Method setCanceledFunc;
    private Method isCanceledFunc;
    private Method unsetCanceledFunc;
    private Method setCurrShelveNbrFunc;
    private Method getCurrShelveNbrFunc;
    private Method getCurrShelveFunc;
    private Method getCurrShelveProductsFunc;
    private Method removeProductFunc;
    private Method isCurrShelveEmptyFunc;

    public MachineSoftwareTest(Class<?> interfaceImpl) {
        this.interfaceImpl = interfaceImpl;
    }

    @Before
    public void setUp() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        m_nbrOfShelves = 3;
        m_machine = new VendingMachine(m_nbrOfShelves);

        m_bars = Product.createProducts(PRODUCT_TYPE.BAR, 5);
        m_cola = Product.createProducts(PRODUCT_TYPE.COLA, 5);
        m_juice = Product.createProducts(PRODUCT_TYPE.JUICE, 5);

        shelveNrb1 = 1;
        shelveNrb2 = 2;
        shelveNrb3 = 3;
        
        m_machine.addProducts(shelveNrb1, m_bars);
        m_machine.addProducts(shelveNrb2, m_cola);
        m_machine.addProducts(shelveNrb3, m_juice);

        m_coins = new Vector<Coin>();

        m_coins.addAll(Coin.createCoins(COIN_TYPE.FIVE_DOLAR, 1));
        m_coins.addAll(Coin.createCoins(COIN_TYPE.TWO_DOLAR, 1));
        m_coins.addAll(Coin.createCoins(COIN_TYPE.ONE_DOLAR, 1));
        m_coins.addAll(Coin.createCoins(COIN_TYPE.FIFTY_CENT, 1));
        m_coins.addAll(Coin.createCoins(COIN_TYPE.TWENTY_CENT, 1));
        m_coins.addAll(Coin.createCoins(COIN_TYPE.TEN_CENT, 1));

        m_machine.addCoins(m_coins);

        paramString[0] = String.class;
        paramInt[0] = Integer.TYPE;
        paramBool[0] = boolean.class;

        cons = interfaceImpl.getDeclaredConstructor(argTypes);
        Object[] args = {m_machine};
        obj = cons.newInstance(args);

        setSoldOutFunc = interfaceImpl.getDeclaredMethod("setSoldOut", paramBool);
        getSoldOutFunc = interfaceImpl.getDeclaredMethod("getSoldOut", noparams);
        getShelvesNbrFunc = interfaceImpl.getDeclaredMethod("getShelvesNbr", noparams);
        getCreditAsStringFunc = interfaceImpl.getDeclaredMethod("getCreditAsString", noparams);
        getCreditCoinsFunc = interfaceImpl.getDeclaredMethod("getCreditCoins", noparams);
        isCreditEmptyFunc = interfaceImpl.getDeclaredMethod("isCreditEmpty", noparams);
        resetCreditFunc = interfaceImpl.getDeclaredMethod("resetCredit", noparams);
        cancelTransactionFunc = interfaceImpl.getDeclaredMethod("cancelTransaction", noparams);
        getAddMoneyAsStringFunc = interfaceImpl.getDeclaredMethod("getAddMoneyAsString", noparams);
        isGiveChangeNeededFunc = interfaceImpl.getDeclaredMethod("isGiveChangeNeeded", noparams);
        isAddMoneyNeededFunc = interfaceImpl.getDeclaredMethod("isAddMoneyNeeded", noparams);
        setCurrPriceFunc = interfaceImpl.getDeclaredMethod("setCurrPrice", paramInt);
        isShelveNbrValidFunc = interfaceImpl.getDeclaredMethod("isShelveNbrValid", paramString);
        isCoinValidFunc = interfaceImpl.getDeclaredMethod("isCoinValid", paramString);
        isGiveChangePossibleFunc = interfaceImpl.getDeclaredMethod("isGiveChangePossible", noparams);
        giveChangeFunc = interfaceImpl.getDeclaredMethod("giveChange", noparams);
        setCanceledFunc = interfaceImpl.getDeclaredMethod("setCanceled", noparams);
        isCanceledFunc = interfaceImpl.getDeclaredMethod("isCanceled", noparams);
        unsetCanceledFunc = interfaceImpl.getDeclaredMethod("unsetCanceled", noparams);
        setCurrShelveNbrFunc = interfaceImpl.getDeclaredMethod("setCurrShelveNbr", paramInt);
        getCurrShelveNbrFunc = interfaceImpl.getDeclaredMethod("getCurrShelveNbr", noparams);
        getCurrShelveFunc = interfaceImpl.getDeclaredMethod("getCurrShelve", noparams);
        getCurrShelveProductsFunc = interfaceImpl.getDeclaredMethod("getCurrShelveProducts", noparams);
        removeProductFunc = interfaceImpl.getDeclaredMethod("removeProduct", noparams);
        isCurrShelveEmptyFunc = interfaceImpl.getDeclaredMethod("isCurrShelveEmpty", noparams);

        setSoldOutFunc.setAccessible(true);
        getSoldOutFunc.setAccessible(true);
        getShelvesNbrFunc.setAccessible(true);
        getCreditAsStringFunc.setAccessible(true);
        getCreditCoinsFunc.setAccessible(true);
        isCreditEmptyFunc.setAccessible(true);
        resetCreditFunc.setAccessible(true);
        cancelTransactionFunc.setAccessible(true);
        getAddMoneyAsStringFunc.setAccessible(true);
        isGiveChangeNeededFunc.setAccessible(true);
        isAddMoneyNeededFunc.setAccessible(true);
        setCurrPriceFunc.setAccessible(true);
        isShelveNbrValidFunc.setAccessible(true);
        isCoinValidFunc.setAccessible(true);
        isGiveChangePossibleFunc.setAccessible(true);
        giveChangeFunc.setAccessible(true);
        setCanceledFunc.setAccessible(true);
        isCanceledFunc.setAccessible(true);
        unsetCanceledFunc.setAccessible(true);
        setCurrShelveNbrFunc.setAccessible(true);
        getCurrShelveNbrFunc.setAccessible(true);
        getCurrShelveFunc.setAccessible(true);
        getCurrShelveProductsFunc.setAccessible(true);
        removeProductFunc.setAccessible(true);
        isCurrShelveEmptyFunc.setAccessible(true);
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(interfaceImpl);
    }

    @Test
    public final void testGetSoldOut() {
        try
        {
            Object value1 = getSoldOutFunc.invoke(obj, (Object[]) null);
            boolean soldOut = (boolean) value1;

            assertTrue(soldOut);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testSetSoldOut() {
        try
        {
            Object value1 = getSoldOutFunc.invoke(obj, (Object[]) null);
            boolean soldOut = (boolean) value1;

            assertTrue(soldOut);

            setSoldOutFunc.invoke(obj, false);

            Object value2 = getSoldOutFunc.invoke(obj, (Object[]) null);
            soldOut = (boolean) value2;

            assertFalse(soldOut);

            setSoldOutFunc.invoke(obj, true);

            Object value3 = getSoldOutFunc.invoke(obj, (Object[]) null);
            soldOut = (boolean) value3;

            assertTrue(soldOut);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetShelvesNbr() {
        try
        {
            Object value1 = getShelvesNbrFunc.invoke(obj, (Object[]) null);
            String shelveNumbers = (String) value1;

            String emptyShelves = "1, 2, 3";

            assertTrue(emptyShelves.compareTo(shelveNumbers) == 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testIsCoinValid() {
        try
        {
            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();
            String twoDolar = COIN_TYPE.TWO_DOLAR.getStrValue();
            String twentyCent = COIN_TYPE.TWENTY_CENT.getStrValue();

            Object value1 = isCoinValidFunc.invoke(obj, fiveDolar);
            boolean coinInserted = (boolean) value1;

            assertTrue(coinInserted);

            Object value2 = isCoinValidFunc.invoke(obj, twoDolar);
            coinInserted = (boolean) value2;

            assertTrue(coinInserted);

            Object value3 = isCoinValidFunc.invoke(obj, twentyCent);
            coinInserted = (boolean) value3;

            assertTrue(coinInserted);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetCreditAsString() {
        try
        {
            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();
            String twoDolar = COIN_TYPE.TWO_DOLAR.getStrValue();
            String twentyCent = COIN_TYPE.TWENTY_CENT.getStrValue();

            isCoinValidFunc.invoke(obj, fiveDolar);
            isCoinValidFunc.invoke(obj, twoDolar);
            isCoinValidFunc.invoke(obj, twentyCent);

            String credit = COIN_TYPE.FIVE_DOLAR.getValue()
                    .add(COIN_TYPE.TWO_DOLAR.getValue())
                    .add(COIN_TYPE.TWENTY_CENT.getValue()).toString();

            Object value4 = getCreditAsStringFunc.invoke(obj, (Object[]) null);
            String currCredit = (String) value4;

            assertEquals(credit.compareTo(currCredit), 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetCreditCoins() {
        try
        {
            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();
            String twoDolar = COIN_TYPE.TWO_DOLAR.getStrValue();
            String twentyCent = COIN_TYPE.TWENTY_CENT.getStrValue();

            isCoinValidFunc.invoke(obj, fiveDolar);
            isCoinValidFunc.invoke(obj, twoDolar);
            isCoinValidFunc.invoke(obj, twentyCent);

            String credit = COIN_TYPE.FIVE_DOLAR.getStrValue() + ", " +
                            COIN_TYPE.TWO_DOLAR.getStrValue()  + ", " +
                            COIN_TYPE.TWENTY_CENT.getStrValue();

            Object value4 = getCreditCoinsFunc.invoke(obj, (Object[]) null);
            String creditCoins = (String) value4;

            assertEquals(credit.compareTo(creditCoins), 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testIsCreditEmpty() {
        try
        {
            Object value1 = isCreditEmptyFunc.invoke(obj, (Object[]) null);
            boolean emptyCredit = (boolean) value1;

            assertTrue(emptyCredit);

            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();
            String twoDolar = COIN_TYPE.TWO_DOLAR.getStrValue();
            String twentyCent = COIN_TYPE.TWENTY_CENT.getStrValue();

            isCoinValidFunc.invoke(obj, fiveDolar);
            isCoinValidFunc.invoke(obj, twoDolar);
            isCoinValidFunc.invoke(obj, twentyCent);

            Object value2 = isCreditEmptyFunc.invoke(obj, (Object[]) null);
            emptyCredit = (boolean) value2;

            assertFalse(emptyCredit);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testResetCredit() {
        try
        {
            Object value1 = isCreditEmptyFunc.invoke(obj, (Object[]) null);
            boolean emptyCredit = (boolean) value1;

            assertTrue(emptyCredit);

            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();
            String twoDolar = COIN_TYPE.TWO_DOLAR.getStrValue();
            String twentyCent = COIN_TYPE.TWENTY_CENT.getStrValue();

            isCoinValidFunc.invoke(obj, fiveDolar);
            isCoinValidFunc.invoke(obj, twoDolar);
            isCoinValidFunc.invoke(obj, twentyCent);

            Object value2 = isCreditEmptyFunc.invoke(obj, (Object[]) null);
            emptyCredit = (boolean) value2;

            assertFalse(emptyCredit);

            resetCreditFunc.invoke(obj, (Object[]) null);

            Object value3 = isCreditEmptyFunc.invoke(obj, (Object[]) null);
            emptyCredit = (boolean) value3;

            assertTrue(emptyCredit);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testCancelTransaction() {
        try
        {
            Object value1 = isCreditEmptyFunc.invoke(obj, (Object[]) null);
            boolean emptyCredit = (boolean) value1;

            assertTrue(emptyCredit);

            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();
            String twoDolar = COIN_TYPE.TWO_DOLAR.getStrValue();
            String twentyCent = COIN_TYPE.TWENTY_CENT.getStrValue();

            isCoinValidFunc.invoke(obj, fiveDolar);
            isCoinValidFunc.invoke(obj, twoDolar);
            isCoinValidFunc.invoke(obj, twentyCent);

            Object value2 = isCreditEmptyFunc.invoke(obj, (Object[]) null);
            emptyCredit = (boolean) value2;

            assertFalse(emptyCredit);

            cancelTransactionFunc.invoke(obj, (Object[]) null);

            Object value3 = isCreditEmptyFunc.invoke(obj, (Object[]) null);
            emptyCredit = (boolean) value3;

            assertTrue(emptyCredit);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetCurrShelveNbr() {
        try
        {
            Object value1 = getCurrShelveNbrFunc.invoke(obj, (Object[]) null);
            int currShelveNbr = (int) value1;

            assertEquals(currShelveNbr, 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testSetCurrShelveNbr() {
        try
        {
            Object value1 = getCurrShelveNbrFunc.invoke(obj, (Object[]) null);
            int currShelveNbr = (int) value1;

            assertEquals(currShelveNbr, 0);

            setCurrShelveNbrFunc.invoke(obj, shelveNrb2);

            Object value2 = getCurrShelveNbrFunc.invoke(obj, (Object[]) null);
            currShelveNbr = (int) value2;

            assertEquals(currShelveNbr, shelveNrb2);

        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetCurrShelve() {
        try
        {
            setCurrShelveNbrFunc.invoke(obj, shelveNrb2);

            Object value1 = getCurrShelveNbrFunc.invoke(obj, (Object[]) null);
            int currShelveNbr = (int) value1;

            assertEquals(currShelveNbr, shelveNrb2);

            Object value2 = getCurrShelveFunc.invoke(obj, (Object[]) null);
            IShelve currShelve = (IShelve) value2;

            assertNotNull(currShelve);
            assertEquals(PRODUCT_TYPE.COLA.getType(), currShelve.getType().getType());
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetCurrShelveProducts() {
        try
        {
            setCurrShelveNbrFunc.invoke(obj, shelveNrb2);

            Object value1 = getCurrShelveNbrFunc.invoke(obj, (Object[]) null);
            int currShelveNbr = (int) value1;

            assertEquals(currShelveNbr, shelveNrb2);

            Object value2 = getCurrShelveProductsFunc.invoke(obj, (Object[]) null);
            @SuppressWarnings("unchecked")
            Vector<IProduct> currShelve = (Vector<IProduct>) value2;

            assertNotNull(currShelve);
            assertTrue(currShelve.size() > 0);
            assertTrue(currShelve.elementAt(1).isSame(m_cola.firstElement()));
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testIsCurrShelveEmpty() {
        try
        {
            setCurrShelveNbrFunc.invoke(obj, shelveNrb2);

            Object value1 = getCurrShelveNbrFunc.invoke(obj, (Object[]) null);
            int currShelveNbr = (int) value1;

            assertEquals(currShelveNbr, shelveNrb2);

            Object value2 = isCurrShelveEmptyFunc.invoke(obj, (Object[]) null);
            boolean isEmpty = (boolean) value2;

            assertFalse(isEmpty);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testIsShelveNbrValid() {
        try
        {
            String shelveNrb2Str = String.valueOf(shelveNrb2);

            Object value1 = isShelveNbrValidFunc.invoke(obj, shelveNrb2Str);
            boolean isValid = (boolean) value1;

            assertTrue(isValid);

            String shelveNrb4Str = String.valueOf(4);

            Object value2 = isShelveNbrValidFunc.invoke(obj, shelveNrb4Str);
            isValid = (boolean) value2;

            assertFalse(isValid);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testRemoveProduct() {
        try
        {
            setCurrShelveNbrFunc.invoke(obj, shelveNrb2);

            Object value1 = getCurrShelveNbrFunc.invoke(obj, (Object[]) null);
            int currShelveNbr = (int) value1;

            assertEquals(currShelveNbr, shelveNrb2);

            Object value2 = getCurrShelveProductsFunc.invoke(obj, (Object[]) null);
            @SuppressWarnings("unchecked")
            Vector<IProduct> currShelve1 = (Vector<IProduct>) value2;
            int nbrOfProd1 = currShelve1.size();

            Object value3 = removeProductFunc.invoke(obj, (Object[]) null);
            IProduct boughtProduct = (IProduct) value3;

            Object value4 = getCurrShelveProductsFunc.invoke(obj, (Object[]) null);
            @SuppressWarnings("unchecked")
            Vector<IProduct> currShelve2 = (Vector<IProduct>) value4;
            int nbrOfProd2 = currShelve2.size();

            assertNotNull(boughtProduct);
            assertTrue(boughtProduct.getType().isEqual(m_cola.firstElement().getType()));
            assertEquals(nbrOfProd1 - 1, nbrOfProd2);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testIsCanceled() {
        try
        {
            Object value1 = isCanceledFunc.invoke(obj, (Object[]) null);
            boolean isCanceled = (boolean) value1;

            assertFalse(isCanceled);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testSetCanceled() {
        try
        {
            Object value1 = isCanceledFunc.invoke(obj, (Object[]) null);
            boolean isCanceled = (boolean) value1;

            assertFalse(isCanceled);

            setCanceledFunc.invoke(obj, (Object[]) null);

            Object value2 = isCanceledFunc.invoke(obj, (Object[]) null);
            isCanceled = (boolean) value2;

            assertTrue(isCanceled);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testUnsetCanceled() {
        try
        {
            Object value1 = isCanceledFunc.invoke(obj, (Object[]) null);
            boolean isCanceled = (boolean) value1;

            assertFalse(isCanceled);

            setCanceledFunc.invoke(obj, (Object[]) null);

            Object value2 = isCanceledFunc.invoke(obj, (Object[]) null);
            isCanceled = (boolean) value2;

            assertTrue(isCanceled);

            unsetCanceledFunc.invoke(obj, (Object[]) null);

            Object value3 = isCanceledFunc.invoke(obj, (Object[]) null);
            isCanceled = (boolean) value3;

            assertFalse(isCanceled);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testSetCurrPrice() {
        try
        {
            setCurrPriceFunc.invoke(obj, shelveNrb2);

            Object value1 = isAddMoneyNeededFunc.invoke(obj, (Object[]) null);
            boolean isEnoughMoney = (boolean) value1;

            assertTrue(isEnoughMoney);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testIsAddMoneyNeeded() {
        try
        {
            setCurrPriceFunc.invoke(obj, shelveNrb2);

            Object value1 = isAddMoneyNeededFunc.invoke(obj, (Object[]) null);
            boolean isEnoughMoney = (boolean) value1;

            assertTrue(isEnoughMoney);

            Object value2 = getAddMoneyAsStringFunc.invoke(obj, (Object[]) null);
            String addMoney = (String) value2;

            assertTrue(m_cola.firstElement().getPrice().toString().compareTo(addMoney) == 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGetAddMoneyAsString() {
        try
        {
            setCurrPriceFunc.invoke(obj, shelveNrb2);

            Object value2 = getAddMoneyAsStringFunc.invoke(obj, (Object[]) null);
            String addMoney = (String) value2;

            assertTrue(m_cola.firstElement().getPrice().toString().compareTo(addMoney) == 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testIsGiveChangeNeeded() {
        try
        {
            setCurrPriceFunc.invoke(obj, shelveNrb2);

            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();

            Object value1 = isCoinValidFunc.invoke(obj, fiveDolar);
            boolean coinInserted = (boolean) value1;

            assertTrue(coinInserted);

            Object value2 = isGiveChangeNeededFunc.invoke(obj, (Object[]) null);
            boolean isChangeNeeded = (boolean) value2;

            assertTrue(isChangeNeeded);

            resetCreditFunc.invoke(obj, (Object[]) null);

            String twentyCent = COIN_TYPE.TWENTY_CENT.getStrValue();

            setCurrPriceFunc.invoke(obj, shelveNrb2);
            Object value3 = isCoinValidFunc.invoke(obj, twentyCent);
            coinInserted = (boolean) value3;

            assertTrue(coinInserted);

            Object value4 = isGiveChangeNeededFunc.invoke(obj, (Object[]) null);
            isChangeNeeded = (boolean) value4;

            assertFalse(isChangeNeeded);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testIsGiveChangePossible() {
        try
        {
            setCurrPriceFunc.invoke(obj, shelveNrb2);

            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();

            Object value1 = isCoinValidFunc.invoke(obj, fiveDolar);
            boolean coinInserted = (boolean) value1;

            assertTrue(coinInserted);

            Object value2 = isGiveChangeNeededFunc.invoke(obj, (Object[]) null);
            boolean isChangeNeeded = (boolean) value2;

            assertTrue(isChangeNeeded);

            Object value3 = isGiveChangePossibleFunc.invoke(obj, (Object[]) null);
            boolean isChangePossible = (boolean) value3;

            assertTrue(isChangePossible);

            Object value4 = giveChangeFunc.invoke(obj, (Object[]) null);
            String change = (String) value4;

            String changeNeeded = "1$, 0.5$, 0.1$"; //price 3.4$, credit 5$

            assertTrue(changeNeeded.compareTo(change) == 0);


            setCurrPriceFunc.invoke(obj, shelveNrb2);

            Object value5 = isCoinValidFunc.invoke(obj, fiveDolar);
            coinInserted = (boolean) value5;

            assertTrue(coinInserted);
            
            Object value6 = isGiveChangeNeededFunc.invoke(obj, (Object[]) null);
            isChangeNeeded = (boolean) value6;

            assertTrue(isChangeNeeded);

            Object value7 = isGiveChangePossibleFunc.invoke(obj, (Object[]) null);
            isChangePossible = (boolean) value7;

            assertFalse(isChangePossible);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public final void testGiveChange() {
        try
        {
            setCurrPriceFunc.invoke(obj, shelveNrb2);

            String fiveDolar = COIN_TYPE.FIVE_DOLAR.getStrValue();

            Object value1 = isCoinValidFunc.invoke(obj, fiveDolar);
            boolean coinInserted = (boolean) value1;

            assertTrue(coinInserted);

            Object value2 = isGiveChangeNeededFunc.invoke(obj, (Object[]) null);
            boolean isChangeNeeded = (boolean) value2;

            assertTrue(isChangeNeeded);

            Object value3 = isGiveChangePossibleFunc.invoke(obj, (Object[]) null);
            boolean isChangePossible = (boolean) value3;

            assertTrue(isChangePossible);

            Object value4 = giveChangeFunc.invoke(obj, (Object[]) null);
            String change = (String) value4;

            String changeNeeded = "1$, 0.5$, 0.1$"; //price 3.4$, credit 5$

            assertTrue(changeNeeded.compareTo(change) == 0);
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @After
    public void tearDown() {

        try {
            setSoldOutFunc.invoke(obj, false);
            resetCreditFunc.invoke(obj, (Object[]) null);

        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() throws ClassNotFoundException {
        return Arrays.asList(new Object[]{
                                            Class.forName("tdd.vendingMachine.impl.software.MachineSoftware")
                                            });
    }
}