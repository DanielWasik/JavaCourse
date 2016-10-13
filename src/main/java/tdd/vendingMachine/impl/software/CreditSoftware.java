package tdd.vendingMachine.impl.software;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Vector;

import tdd.vendingMachine.impl.coin.Coin;

public class CreditSoftware {

    private Vector<Coin> m_credit;

    public CreditSoftware() {
        m_credit = new Vector<Coin>();
    }

    protected String getValueAsString() {
        return String.valueOf(this.getValue());
    }

    protected BigDecimal getValue() {
        BigDecimal sum = new BigDecimal("0.0");

        Enumeration<Coin> e = m_credit.elements();
        while (e.hasMoreElements())
        {
            sum = sum.add(e.nextElement().getValue());
        }

        return sum;
    }

    protected String getCreditCoins() {
        String coins = "";

        Enumeration<Coin> e = m_credit.elements();
        while(e.hasMoreElements())
        {
            coins += (e.nextElement().getType().getStrValue() + ", ");
        }

        return (coins.isEmpty() ?  coins : coins.substring(0, coins.length() - 2));
    }

    protected void addCoin(Coin coin) {
        m_credit.add(coin);
    }

    protected boolean isEmpty() {
        return m_credit.isEmpty();
    }

    protected void reset() {
        m_credit.removeAllElements();
    }

    protected Vector<Coin> getCoins() {
        return m_credit;
    }
}