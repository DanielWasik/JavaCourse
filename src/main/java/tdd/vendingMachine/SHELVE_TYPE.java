package tdd.vendingMachine;

import java.math.BigDecimal;

public enum SHELVE_TYPE implements IProductType {

    EMPTY("No products", 0, new BigDecimal("0.0"));

    private String m_desc;
    private int m_type;
    private BigDecimal m_price;

    private SHELVE_TYPE(String desc, int type, BigDecimal price) {
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
    public boolean isEqual(IProductType other) {

        if (other == null || this.getType() != other.getType())
        {
            return false;
        }

        return true;
    }

    @Override
    public BigDecimal getPrice() {
        return m_price;
    }
}