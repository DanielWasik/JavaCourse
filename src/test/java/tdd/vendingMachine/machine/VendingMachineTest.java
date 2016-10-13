package tdd.vendingMachine.machine;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import tdd.vendingMachine.ICoin;
import tdd.vendingMachine.IProduct;
import tdd.vendingMachine.IProductType;
import tdd.vendingMachine.IShelve;
import tdd.vendingMachine.IVendingMachine;
import tdd.vendingMachine.impl.exceptions.IllegalClassArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalEmptyArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalOutOfRangeArgumentException;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class VendingMachineTest {

    private IVendingMachine interfaceImpl;
    private Product product1, product2, product3;
    private Vector<Product> products;
    private int shelveNbr;
    private Coin coin1, coin2, coin3;
    private Vector<Coin> coins;
    private BigDecimal change1, change2, zeroChange;

    @Before
    public void setUp() {
        product1 = new Product();
        product2 = new Product();
        product3 = new Product();

        shelveNbr = 1;

        products = new Vector<Product>();
        products.add(product2);
        products.add(product3);
        
        coin1 = new Coin();
        coin2 = new Coin();
        coin3 = new Coin();

        coins = new Vector<Coin>();
        coins.add(coin1);
        coins.add(coin2);
        coins.add(coin3);

        change1 = new BigDecimal("0.5");
        change2 = new BigDecimal("1.2");
        zeroChange = new BigDecimal("0");
    }

    public VendingMachineTest(IVendingMachine interfaceImpl) {
        this.interfaceImpl = interfaceImpl;
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(interfaceImpl);
    }

    @Test(expected = IllegalOutOfRangeArgumentException.class)
    public final void testAddProductWrongShelve() {
        interfaceImpl.addProduct(0, product1);
    }

    @Test(expected = IllegalNullArgumentException.class)
    public final void testAddProductNullProduct() {
        interfaceImpl.addProduct(shelveNbr, null);
    }

    @Test(expected = IllegalClassArgumentException.class)
    public final void testAddProductWrongClass() {
        CustomProduct differentProduct = new CustomProduct();
        interfaceImpl.addProduct(shelveNbr, differentProduct);
    }

    @Test(expected = IllegalOutOfRangeArgumentException.class)
    public final void testAddProductWrongType() {

        interfaceImpl.addProduct(shelveNbr, product1);
        interfaceImpl.addProduct(shelveNbr, product2);
    }

    @Test
    public final void testAddProduct() {
        interfaceImpl.addProduct(shelveNbr, product1);

        assertNotNull(interfaceImpl.getShelves());
        assertNotNull(interfaceImpl.getShelves().get(shelveNbr));
        assertNotNull(interfaceImpl.getShelves().get(shelveNbr).getProducts());

        assertTrue(interfaceImpl.getShelves().get(shelveNbr).getNbrOfProducts() == 1);
        assertTrue(product1.isSame(interfaceImpl.getShelves().get(shelveNbr).getProducts().elementAt(0)));
    }

    @Test(expected = IllegalEmptyArgumentException.class)
    public final void testAddProductsEmptyVector() {
        Vector<Product> empty = new Vector<Product>();
        interfaceImpl.addProducts(1, empty);
    }

    @Test
    public final void testAddProducts() {
        int nbrOfProducts = products.size();
        interfaceImpl.addProducts(shelveNbr, products);

        assertNotNull(interfaceImpl.getShelves());
        assertNotNull(interfaceImpl.getShelves().get(shelveNbr));
        assertNotNull(interfaceImpl.getShelves().get(shelveNbr).getProducts());
        assertTrue(nbrOfProducts == interfaceImpl.getShelves().get(shelveNbr).getProducts().size());
    }

    @Test
    public final void testGetShelves() {
        Map<Integer, IShelve> shelves = interfaceImpl.getShelves();

        assertNotNull(shelves);
        assertTrue(shelves.size() >= 0);
    }

    @Test(expected = IllegalNullArgumentException.class)
    public final void testInsertCoinNullCoin() {
        interfaceImpl.insertCoin(null);
    }

    @Test(expected = IllegalClassArgumentException.class)
    public final void testInsertCoinWrongClass() {
        CustomCoin customCoin = new CustomCoin();
        interfaceImpl.insertCoin(customCoin);
    }

    @Test
    public final void testInsertCoin() {
        assertTrue(interfaceImpl.insertCoin(coin1));
    }

    @Test(expected = IllegalEmptyArgumentException.class)
    public final void testAddCoinsEmptyVector() {
        Vector<Coin> empty = new Vector<Coin>();
        interfaceImpl.addCoins(empty);
    }

    @Test
    public final void testGetBackCoins() {
        interfaceImpl.addCoins(coins);
        assertTrue(interfaceImpl.getBackCoins(coins));
    }

    @Test
    public final void testGiveChange() {
        Vector<Coin> givenChange = interfaceImpl.giveChange(change1);
        assertTrue(givenChange != null && givenChange.size() == 0);
    }

    @Test
    public final void testIsChangePossible() {

        assertTrue(interfaceImpl.isChangePossible(zeroChange));

        interfaceImpl.insertCoin(coin3);
        assertTrue(interfaceImpl.isChangePossible(change1));
        assertFalse(interfaceImpl.isChangePossible(change2));
    }

    @After
    public void tearDown() {
        Vector<Coin> moreCoins = new Vector<Coin>();
        moreCoins.add(coin1);
        moreCoins.add(coin2);
        moreCoins.add(coin3);

        interfaceImpl.getBackCoins(coins);
        interfaceImpl.getBackCoins(moreCoins);

        if (interfaceImpl.getShelves() != null && interfaceImpl.getShelves().get(shelveNbr) != null)
        {
            int size = interfaceImpl.getShelves().get(shelveNbr).getNbrOfProducts();
            IShelve shelve = interfaceImpl.getShelves().get(shelveNbr);
            for (int i = 0; i < size; ++i)
            {
                shelve.removeProduct();
            }
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[]{
                                            new DummyVendingMachine()
                                            });
    }
}

class CustomProduct implements IProduct {

    @Override
    public IProductType getType() {
        return null;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public boolean isSame(IProduct other) {
        return false;
    }
}

class Coin implements ICoin {

    @Override
    public BigDecimal getValue() {
        return null;
    }

}

class CustomCoin implements ICoin {

    @Override
    public BigDecimal getValue() {
        return null;
    }

}

class DummyVendingMachine implements IVendingMachine {

    @Override
    public Map<Integer, IShelve> getShelves() {
        return null;
    }

    @Override
    public <T extends ICoin> boolean insertCoin(T coin) {
        return false;
    }

    @Override
    public <T extends ICoin> void addCoins(Vector<T> coins) {

    }

    @Override
    public <T extends ICoin> boolean getBackCoins(Vector<T> coins) {
        return false;
    }

    @Override
    public <T extends ICoin> Vector<T> giveChange(BigDecimal change) {
        return null;
    }

    @Override
    public boolean isChangePossible(BigDecimal change) {
        return false;
    }

    @Override
    public <T extends IProduct> void addProducts(int shelveNbr, Vector<T> products) {
    }

    @Override
    public <T extends IProduct> void addProduct(int shelveNbr, T product) {
    }
}