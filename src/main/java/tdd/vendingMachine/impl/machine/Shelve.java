package tdd.vendingMachine.impl.machine;

import java.util.Vector;

import tdd.vendingMachine.IProduct;
import tdd.vendingMachine.IProductType;
import tdd.vendingMachine.IShelve;
import tdd.vendingMachine.PRODUCT_TYPE;
import tdd.vendingMachine.SHELVE_TYPE;
import tdd.vendingMachine.impl.exceptions.IllegalClassArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalEmptyArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalOutOfRangeArgumentException;
import tdd.vendingMachine.impl.messages.ERROR;
import tdd.vendingMachine.impl.product.Product;
import tdd.vendingMachine.impl.util.Util;

public class Shelve implements IShelve {

    private IProductType m_type;
    private int m_nbrOfProducts;
    private Vector<IProduct> v_products;

    public Shelve() {
        m_type = SHELVE_TYPE.EMPTY;
        m_nbrOfProducts = 0;
        v_products = new Vector<IProduct>(0);
    }

    public Shelve(PRODUCT_TYPE productType, Vector<Product> products) throws IllegalNullArgumentException, IllegalEmptyArgumentException {
        Util.checkNotNull(productType, "productType");
        Util.checkIsEmpty(products, "products");

        for (IProduct product : products)
        {
            if (productType.isEqual(product.getType()))
            {
                v_products.addElement(product);
            }
        }

        m_type = productType;
        m_nbrOfProducts = v_products.size();
    }

    @Override
    public <T extends IProduct> void addProduct(T product)
            throws IllegalNullArgumentException, IllegalOutOfRangeArgumentException,
            IllegalClassArgumentException {
        Util.checkNotNull(product, "product");
        //Util.<Product, T, IProduct>checkClass(product, "product");

        if (m_nbrOfProducts != 0 && !m_type.isEqual(product.getType()))
        {
            throw new IllegalOutOfRangeArgumentException(ERROR.WRONG_PRODUCT.get());
        }

        try
        {
            v_products.addElement((IProduct) product);
            m_type = ((Product) product).getType();
            m_nbrOfProducts++;
        }
        catch (ClassCastException e)
        {
            throw new IllegalClassArgumentException("product" + ERROR.WRONG_CLASS_ARGUMENT.get());
        }
    }

    @Override
    public Vector<IProduct> getProducts() {
        return v_products;
    }

    @Override
    public int getNbrOfProducts() {
        return m_nbrOfProducts;
    }

    @Override
    public IProductType getType() {
        return m_type;
    }

    @Override
    public boolean isEmpty() {
        return v_products.isEmpty();
    }

    @Override
    public IProduct removeProduct() {
        if (this.isEmpty())
        {
            return null;
        }

        if (m_nbrOfProducts == 1)
        {
            m_type = SHELVE_TYPE.EMPTY;
        }

        m_nbrOfProducts--;
        return v_products.remove(m_nbrOfProducts);
    }
}