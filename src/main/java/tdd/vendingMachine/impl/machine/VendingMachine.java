package tdd.vendingMachine.impl.machine;

import tdd.vendingMachine.ICoin;
import tdd.vendingMachine.IProduct;
import tdd.vendingMachine.IShelve;
import tdd.vendingMachine.IVendingMachine;
import tdd.vendingMachine.impl.coin.Coin;
import tdd.vendingMachine.impl.exceptions.IllegalClassArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalEmptyArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalOutOfRangeArgumentException;
import tdd.vendingMachine.impl.machine.Shelve;
import tdd.vendingMachine.impl.messages.ERROR;
import tdd.vendingMachine.impl.product.Product;
import tdd.vendingMachine.impl.util.Util;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class VendingMachine implements IVendingMachine {

    private Map<Integer, IShelve> m_shelves;
    private CoinBox m_coinBox;

    @SuppressWarnings("unused")
    private VendingMachine() {

    }

    public VendingMachine(int nbrOfShelves)
            throws IllegalNullArgumentException, IllegalOutOfRangeArgumentException {
        Util.checkIsPositive(nbrOfShelves, "nbrOfShelves");

        m_coinBox = new CoinBox();
        m_shelves = new HashMap<Integer, IShelve>();

        for (int i = 1; i <= nbrOfShelves; ++i)
        {
            m_shelves.put(i, new Shelve());
        }
    }

    @SuppressWarnings("hiding")
    @Override
    public <Product extends IProduct> void addProduct(int shelveNbr, Product product)
            throws IllegalNullArgumentException, IllegalOutOfRangeArgumentException,
            IllegalClassArgumentException {
        Util.checkIsPositive(shelveNbr, "shelveNbr");
        Util.checkNotNull(product, "product");

        if (!m_shelves.containsKey(shelveNbr))
        {
            throw new IllegalOutOfRangeArgumentException(shelveNbr + ERROR.OUT_OF_RANGE_ARGUMENT.get() + "shelveNbr");
        }

        try {
            Shelve shelve = (Shelve) m_shelves.get(shelveNbr);
            shelve.addProduct(product);
        }
        catch (ClassCastException e) {
            throw new IllegalClassArgumentException("product" + ERROR.WRONG_CLASS_ARGUMENT.get());
        }
    }

    @SuppressWarnings("hiding")
    @Override
    public <Product extends IProduct> void addProducts(int shelveNbr, Vector<Product> products)
            throws IllegalNullArgumentException, IllegalOutOfRangeArgumentException,
            IllegalEmptyArgumentException, IllegalClassArgumentException {
        Util.checkIsPositive(shelveNbr, "shelveNbr");
        Util.checkIsEmpty(products, "products");

        Enumeration<Product> e = products.elements();

        while (e.hasMoreElements())
        {
            addProduct(shelveNbr, e.nextElement());
        }
    }

    @Override
    public Map<Integer, IShelve> getShelves() {
        return m_shelves;
    }

    @Override
    public <T extends ICoin> boolean insertCoin(T coin)
            throws IllegalNullArgumentException, IllegalClassArgumentException {

        try {
            return m_coinBox.addCoin((Coin) coin);
        }
        catch (ClassCastException e) {
            throw new IllegalClassArgumentException("coin" + ERROR.WRONG_CLASS_ARGUMENT.get());
        }
    }

    @SuppressWarnings("hiding")
    @Override
    public <Coin extends ICoin> void addCoins(Vector<Coin> coins)
            throws IllegalNullArgumentException, IllegalEmptyArgumentException, IllegalClassArgumentException {
        Util.checkIsEmpty(coins, "coins");

        Enumeration<Coin> e = coins.elements();

        while (e.hasMoreElements())
        {
            insertCoin(e.nextElement());
        }
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public <T extends ICoin> boolean getBackCoins(Vector<T> coins)
            throws IllegalClassArgumentException {

        try
        {
            return m_coinBox.removeCoins((Vector<Coin>)coins);
        }
        catch (ClassCastException e)
        {
            throw new IllegalClassArgumentException("coins" + ERROR.WRONG_CLASS_ARGUMENT.get());
        }
    }

    @Override
    public boolean isChangePossible(BigDecimal change) {
        return m_coinBox.isChangePossible(change);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Vector<Coin> giveChange(BigDecimal change) {
        return m_coinBox.giveChange();
    }
}