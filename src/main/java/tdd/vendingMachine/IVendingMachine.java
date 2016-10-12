package tdd.vendingMachine;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Vector;

public interface IVendingMachine {

    public abstract Map<Integer, IShelve> getShelves();

    public abstract <T extends ICoin> boolean insertCoin(T coin);

    public abstract <T extends ICoin> void addCoins(Vector<T> coins);

    public abstract <T extends ICoin> boolean getBackCoins(Vector<T> coins);

    public abstract <T extends ICoin> Vector<T> giveChange(BigDecimal change);

    public abstract boolean isChangePossible(BigDecimal change);

    public abstract <T extends IProduct> void addProducts(int shelveNbr, Vector<T> products);

    public abstract <T extends IProduct> void addProduct(int shelveNbr, T product);
}