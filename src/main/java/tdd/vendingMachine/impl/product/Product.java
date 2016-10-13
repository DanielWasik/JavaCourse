package tdd.vendingMachine.impl.product;

import java.math.BigDecimal;
import java.util.Vector;

import tdd.vendingMachine.IProduct;
import tdd.vendingMachine.IProductType;
import tdd.vendingMachine.PRODUCT_TYPE;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalOutOfRangeArgumentException;
import tdd.vendingMachine.impl.util.Util;

public class Product implements IProduct {

    private IProductType m_productType;
    private BigDecimal m_price;

    private Product() {

    };

    private Product(PRODUCT_TYPE productType)
            throws IllegalNullArgumentException {
        Util.checkNotNull(productType, "productType");

        m_productType = productType;
        m_price = m_productType.getPrice();
    }

    @Override
    public IProductType getType() {
        return m_productType;
    }

    @Override
    public BigDecimal getPrice() {
        return m_price;
    }

    @Override
    public String getDesc() {
        return m_productType.getDesc();
    }

    @Override
    public boolean isSame(IProduct other) {
        return (other == null ? false : m_productType.isEqual(other.getType()));
    }

    public static Vector<Product> createProducts(PRODUCT_TYPE productType, int quantity)
            throws IllegalNullArgumentException, IllegalOutOfRangeArgumentException {
        Util.checkNotNull(productType, "productType");
        Util.checkIsPositive(quantity, "quantity");

        Vector<Product> products = new Vector<Product>();

        for (int i = 0; i < quantity; ++i)
        {
            products.add(new Product(productType));
        }

        return products;
    }
}