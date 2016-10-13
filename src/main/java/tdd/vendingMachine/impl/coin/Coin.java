package tdd.vendingMachine.impl.coin;

import java.math.BigDecimal;
import java.util.Vector;

import tdd.vendingMachine.ICoin;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalOutOfRangeArgumentException;
import tdd.vendingMachine.impl.util.Util;

public class Coin implements ICoin {

    private BigDecimal m_value;
    private COIN_TYPE m_coinType;

    @SuppressWarnings("unused")
    private Coin() {

    };

    public Coin(COIN_TYPE coinType)
            throws IllegalNullArgumentException {
        Util.checkNotNull(coinType, "coinType");
        m_value = coinType.getValue();
        m_coinType = coinType;
    }

    @Override
    public BigDecimal getValue() {
        return m_value;
    }

    public COIN_TYPE getType() {
        return m_coinType;
    }

    public static Vector<Coin> createCoins(COIN_TYPE coinType, int quantity)
            throws IllegalNullArgumentException, IllegalOutOfRangeArgumentException {
        Util.checkNotNull(coinType, "coinType");
        Util.checkIsPositive(quantity, "quantity");

        Vector<Coin> coins = new Vector<Coin>();

        for (int i = 0; i < quantity; ++i)
        {
            coins.add(new Coin(coinType));
        }

        return coins;
    }
}