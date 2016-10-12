package tdd.vendingMachine;

import java.math.BigDecimal;

public interface IProductType {

    public abstract int getType();

    public abstract String getDesc();

    public abstract BigDecimal getPrice();

    public abstract boolean isEqual(IProductType other);
}