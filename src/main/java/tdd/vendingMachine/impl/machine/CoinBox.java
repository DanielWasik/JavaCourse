package tdd.vendingMachine.impl.machine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import tdd.vendingMachine.impl.coin.COIN_TYPE;
import tdd.vendingMachine.impl.coin.Coin;
import tdd.vendingMachine.impl.exceptions.IllegalClassArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.messages.ERROR;
import tdd.vendingMachine.impl.util.Util;

public class CoinBox {

    static BigDecimal m_zeroChange = new BigDecimal("0.0");

    private LinkedHashMap<COIN_TYPE, Vector<Coin>> m_coinSlots;
    private Vector<Coin> m_change;

    public CoinBox() {
        m_coinSlots = new LinkedHashMap<COIN_TYPE, Vector<Coin>>();

        m_coinSlots.put(COIN_TYPE.FIVE_DOLAR, new Vector<Coin>());
        m_coinSlots.put(COIN_TYPE.TWO_DOLAR, new Vector<Coin>());
        m_coinSlots.put(COIN_TYPE.ONE_DOLAR, new Vector<Coin>());
        m_coinSlots.put(COIN_TYPE.FIFTY_CENT, new Vector<Coin>());
        m_coinSlots.put(COIN_TYPE.TWENTY_CENT, new Vector<Coin>());
        m_coinSlots.put(COIN_TYPE.TEN_CENT, new Vector<Coin>());

        m_change = new Vector<Coin>();
    }
    
    protected boolean addCoin(Coin coin)
            throws IllegalNullArgumentException, IllegalClassArgumentException {
        Util.checkNotNull(coin, "coin");

        try
        {
            return m_coinSlots.get(coin.getType()).add(coin);
        }
        catch (ClassCastException e)
        {
            throw new IllegalClassArgumentException("product" + ERROR.WRONG_CLASS_ARGUMENT.get());
        }
    }

    protected boolean removeCoins(Vector<Coin> coins) {
        if (coins == null || coins.isEmpty())
        {
            return false;
        }

        int counter = coins.size();
        Enumeration<Coin> e = coins.elements();

        while (e.hasMoreElements())
        {
            Coin coin = e.nextElement();

            if (!m_coinSlots.get(coin.getType()).isEmpty())
            {
                m_coinSlots.get(coin.getType()).remove(0);
                counter--;
            }
        }

        return (counter == 0 ? true : false);
    }

    protected void saveChange(Vector<Coin> coinSlot, int nbrOfCoins) {
        m_change.addAll(coinSlot.subList(0, nbrOfCoins));
    }

    protected void resetChange() {
        m_change.removeAllElements();
    }

    protected Vector<Coin> giveChange() {
        removeCoins(m_change);
        Vector<Coin> change = new Vector<Coin>(m_change);
        resetChange();
        return change;
    }

    protected boolean isChangePossible(BigDecimal change) {
        boolean changeFound = true;
        resetChange();

        Iterator<Map.Entry<COIN_TYPE, Vector<Coin> >> entries = m_coinSlots.entrySet().iterator();

        while (change.compareTo(m_zeroChange) > 0 && entries.hasNext())
        {
            Vector<Coin> coins = entries.next().getValue();

            if (coins.size() > 0)
            {
                BigDecimal haveNbrOfCoins = new BigDecimal(coins.size());
                BigDecimal nominal = coins.elementAt(0).getValue();

                if (change.compareTo(nominal) >= 0)
                {
                    BigDecimal nbrOfCoins = change.divide(nominal, 0, RoundingMode.DOWN);
                    nbrOfCoins = nbrOfCoins.min(haveNbrOfCoins);
                    change = change.subtract(nominal.multiply(nbrOfCoins));
                    saveChange(coins, nbrOfCoins.intValue());
                }
            }
        }

        if (change.compareTo(m_zeroChange) > 0)
        {
            changeFound = false;
            resetChange();
        }

        return changeFound;
    }
}