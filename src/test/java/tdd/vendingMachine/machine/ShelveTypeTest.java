package tdd.vendingMachine.machine;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import tdd.vendingMachine.IProductType;
import tdd.vendingMachine.SHELVE_TYPE;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.JVM)
public class ShelveTypeTest {

    private IProductType interfaceImpl;

    private int productType = SHELVE_TYPE.EMPTY.getType();
    private BigDecimal productPrice = SHELVE_TYPE.EMPTY.getPrice();
    private String productDesc = SHELVE_TYPE.EMPTY.getDesc();

    public ShelveTypeTest(IProductType interfaceImpl) {
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
        assertTrue(interfaceImpl.isEqual(SHELVE_TYPE.EMPTY));
    }

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        return Arrays.asList(new Object[]{
                                        SHELVE_TYPE.EMPTY
                                        });
    }
}