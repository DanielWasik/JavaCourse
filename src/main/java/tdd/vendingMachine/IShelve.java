package tdd.vendingMachine;

import java.util.Vector;

public interface IShelve {

    public abstract <T extends IProduct> void addProduct(T product);

    public abstract int getNbrOfProducts();

    public abstract IProductType getType();

    public abstract boolean isEmpty();

    public abstract IProduct removeProduct();

    public abstract Vector<IProduct> getProducts();
}