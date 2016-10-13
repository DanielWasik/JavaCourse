package tdd.vendingMachine.impl.coin;

import java.math.BigDecimal;

import tdd.vendingMachine.impl.messages.INFORMATION;

public enum COIN_TYPE {

    FIVE_DOLAR(new BigDecimal("5")),
    TWO_DOLAR(new BigDecimal("2")),
    ONE_DOLAR(new BigDecimal("1")),

    FIFTY_CENT(new BigDecimal("0.5")),
    TWENTY_CENT(new BigDecimal("0.2")),
    TEN_CENT(new BigDecimal("0.1"));

    private BigDecimal m_numVal;

    private COIN_TYPE(BigDecimal numVal) {
        this.m_numVal = numVal;
    }

    public BigDecimal getValue() {
        return m_numVal;
    }

    public String getStrValue() {
        return String.valueOf(m_numVal) + INFORMATION.CURRENCY.get();
    }

    static public boolean hasValue(String coinType) {
        if (coinType == null || coinType.isEmpty())
        {
            return false;
        }

        for (COIN_TYPE c : COIN_TYPE.values())
        {
            if (coinType.compareTo(c.getStrValue()) == 0)
            {
                return true;
            }
        }

        return false;
    }

    static public COIN_TYPE getEnumFromString(String coinType) {
        if (!hasValue(coinType))
        {
            return null;
        }

        for (COIN_TYPE c : COIN_TYPE.values())
        {
            if (coinType.compareTo(c.getStrValue()) == 0)
            {
                return c;
            }
        }

        return null;
    }

    static public String getAll() {
        String result = "";

        for (COIN_TYPE c : COIN_TYPE.values())
        {
            result += c.getStrValue() + ", ";
        }

        return (result.isEmpty() ? result : result.substring(0, result.length() - 2));
    };
}