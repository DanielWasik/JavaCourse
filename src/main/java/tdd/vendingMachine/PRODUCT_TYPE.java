package tdd.vendingMachine;

import java.math.BigDecimal;

public enum PRODUCT_TYPE implements IProductType {

    COLA("Cola drink 0.25l", 1, new BigDecimal("3.4")),
    WATER("Mineral water 0.33l", 2, new BigDecimal("1.9")),
    BAR("Chocolate bar", 3, new BigDecimal("2.7")),
    CHIPS("Lays classic 200g", 4, new BigDecimal("4.5")),
    GUM("Winterfresh 6 sticks", 5, new BigDecimal("3.0")),
    JUICE("Tymbark apple-mint 0.33l", 6, new BigDecimal("5"));

    private String m_desc;
    private int m_type;
    private BigDecimal m_price;

    private PRODUCT_TYPE(String desc, int type, BigDecimal price) {
        this.m_desc = desc;
        this.m_type = type;
        this.m_price = price;
    }

    @Override
    public String getDesc() {
        return m_desc;
    }

    @Override
    public int getType() {
        return m_type;
    }

    @Override
    public BigDecimal getPrice() {
        return m_price;
    }

    @Override
    public boolean isEqual(IProductType other) {

        if (other == null || this.getType() != other.getType())
        {
            return false;
        }

        return true;
    }
}