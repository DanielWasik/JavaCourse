package tdd.vendingMachine.product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import tdd.vendingMachine.IProductType;
import tdd.vendingMachine.PRODUCT_TYPE;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class ProductTypeTest {

    private IProductType interfaceImpl;

    private int productType = PRODUCT_TYPE.WATER.getType();
    private BigDecimal productPrice = PRODUCT_TYPE.WATER.getPrice();
    private String productDesc = PRODUCT_TYPE.WATER.getDesc();

    public ProductTypeTest(IProductType interfaceImpl) {
        this.interfaceImpl = interfaceImpl;
    }

    @Test
    public final void testClassObjectNotNull() {
        assertNotNull(interfaceImpl);
    }

    @Test
    public final void testGetType() {
        assertTrue(productType == interfaceImpl.getType());
    }

    @Test
    public final void testGetPrice() {
        assertTrue(productPrice.compareTo(interfaceImpl.getPrice()) == 0);
    }

    @Test
    public final void testGetDesc() {
        assertTrue(productDesc.compareTo(interfaceImpl.getDesc()) == 0);
    }

    @Test
    public final void testIsEqual() {
        assertTrue(interfaceImpl.isEqual(PRODUCT_TYPE.WATER));
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[]{
                                        PRODUCT_TYPE.WATER
                                        });
    }
}