package tdd.vendingMachine.machine;

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
import tdd.vendingMachine.PRODUCT_TYPE;
import tdd.vendingMachine.SHELVE_TYPE;
import tdd.vendingMachine.impl.machine.Shelve;
import tdd.vendingMachine.impl.product.Product;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class ShelveTest {

    private IShelve interfaceImpl;
    private Product product1, product2;

    @Before
    public void setUp() {
        product1 = Product.createProducts(PRODUCT_TYPE.COLA, 1).firstElement();
        product2 = Product.createProducts(PRODUCT_TYPE.COLA, 1).firstElement();
    }

    public ShelveTest(IShelve interfaceImpl) {
        this.interfaceImpl = interfaceImpl;
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(interfaceImpl);
    }

    @Test
    public final void testGetNbrOfProducts() {
        assertTrue(interfaceImpl.getNbrOfProducts() == 0);

        interfaceImpl.addProduct(product1);

        assertTrue(interfaceImpl.getNbrOfProducts() == 1);

        interfaceImpl.addProduct(product2);

        assertTrue(interfaceImpl.getNbrOfProducts() == 2);
    }

    @Test
    public final void testAddProduct() {
        int beforeAdd = interfaceImpl.getNbrOfProducts();
        interfaceImpl.addProduct(product1);
        int afterAdd = interfaceImpl.getNbrOfProducts();

        assertTrue(afterAdd - beforeAdd == 1);
    }

    @Test
    public final void testGetType() {
        assertTrue(interfaceImpl.getType().isEqual(SHELVE_TYPE.EMPTY));
        interfaceImpl.addProduct(product1);
        assertFalse(interfaceImpl.getType().isEqual(SHELVE_TYPE.EMPTY));
    }

    @Test
    public final void testIsEmpty() {
        assertTrue(interfaceImpl.isEmpty());
        interfaceImpl.addProduct(product1);
        assertFalse(interfaceImpl.isEmpty());
    }

    @Test
    public final void testRemoveProduct() {
        assertNull(interfaceImpl.removeProduct());

        interfaceImpl.addProduct(product1);
        assertNotNull(interfaceImpl.removeProduct());
        assertTrue(interfaceImpl.isEmpty());

        interfaceImpl.addProduct(product1);
        interfaceImpl.addProduct(product1);
        IProduct removedProduct = interfaceImpl.removeProduct();
        assertTrue(product1.isSame(removedProduct));
    }

    @Test
    public final void testGetProducts() {
        interfaceImpl.addProduct(product1);
        interfaceImpl.addProduct(product1);

        Vector<IProduct> products = interfaceImpl.getProducts();
        assertNotNull(products);
        assertTrue(products.size() == 2);
        assertTrue(product1.isSame(products.elementAt(0)) && product1.isSame(products.elementAt(1)));
    }

    @After
    public void tearDown() {

        if (interfaceImpl.getProducts() != null)
        {
            int size = interfaceImpl.getProducts().size();
            for (int i = 0; i < size; ++i)
            {
                interfaceImpl.removeProduct();
            }
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[]{
                                            new Shelve()
                                            });
    }
}