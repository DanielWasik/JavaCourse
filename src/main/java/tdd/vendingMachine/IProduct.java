package tdd.vendingMachine;

import java.math.BigDecimal;

public interface IProduct {

    public abstract IProductType getType();

    public abstract BigDecimal getPrice();

    public abstract String getDesc();

    public abstract boolean isSame(IProduct other);
}