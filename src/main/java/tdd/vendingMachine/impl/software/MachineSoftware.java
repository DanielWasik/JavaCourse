package tdd.vendingMachine.impl.software;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import tdd.vendingMachine.IProduct;
import tdd.vendingMachine.IShelve;
import tdd.vendingMachine.IVendingMachine;
import tdd.vendingMachine.impl.coin.COIN_TYPE;
import tdd.vendingMachine.impl.coin.Coin;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.messages.WARNING;
import tdd.vendingMachine.impl.util.Util;

public class MachineSoftware {

    private IVendingMachine m_machine;
    private Map<Integer, IShelve> m_shelves;

    private CreditSoftware m_credit;
    private BigDecimal m_currPrice;
    private int m_currShelveNbr;

    private boolean m_soldOut;
    private boolean m_cenceled;

    public MachineSoftware(IVendingMachine machine)
            throws IllegalNullArgumentException {
        Util.checkNotNull(machine, "machine");

        m_machine = machine;
        m_credit = new CreditSoftware();

        m_shelves = m_machine.getShelves();
        m_soldOut = true;
        m_cenceled = false;
    }

    protected void setSoldOut(boolean soldOut) {
        m_soldOut = soldOut;
    }

    protected boolean getSoldOut() {
        return m_soldOut;
    }

    protected String getShelvesNbr() {
        String shelveNbr = "";

        for (Map.Entry<Integer, IShelve> entry : m_shelves.entrySet())
        {
            if (!entry.getValue().getProducts().isEmpty())
            {
                setSoldOut(false);
                shelveNbr += entry.getKey() + ", ";
            }
        }

        return (shelveNbr.isEmpty() ?  WARNING.SOLD_OUT.get() : shelveNbr.substring(0, shelveNbr.length() - 2));
    }

    protected String getCreditAsString() {
        return m_credit.getValueAsString();
    }

    protected String getCreditCoins() {
        return m_credit.getCreditCoins();
    }

    protected Boolean isCreditEmpty() {
        return m_credit.isEmpty();
    }

    protected void resetCredit() {
        m_credit.reset();
    }

    protected void cancelTransaction() {
        m_machine.getBackCoins(m_credit.getCoins());
        m_credit.reset();
    }

    protected String getAddMoneyAsString() {
        BigDecimal moneyToAdd = m_currPrice.subtract(m_credit.getValue());

        return String.valueOf((moneyToAdd.compareTo(new BigDecimal("0")) > 0 ? moneyToAdd : "0.0"));
    }

    protected boolean isGiveChangeNeeded() {
        BigDecimal change = m_credit.getValue().subtract(m_currPrice);

        return (change.compareTo(new BigDecimal("0")) > 0 ? true : false);
    }

    protected boolean isAddMoneyNeeded() {
        BigDecimal moneyToAdd = m_currPrice.subtract(m_credit.getValue());

        return (moneyToAdd.compareTo(new BigDecimal("0")) > 0 ? true : false);
    }

    protected void setCurrPrice(int shelveNbr) {
        m_currPrice = m_shelves.get(shelveNbr).getProducts().firstElement().getPrice();
    }

    protected boolean isShelveNbrValid(String userInput) {
        if (!Util.isInteger(userInput))
        {
            return false;
        }

        int shelveNbr = Integer.parseInt(userInput);

        if (!m_shelves.containsKey(shelveNbr))
        {
            return false;
        }

        if (m_shelves.get(shelveNbr).getProducts().size() == 0)
        {
            return false;
        }

        setCurrShelveNbr(shelveNbr);
        setCurrPrice(shelveNbr);

        return true;
    }

    protected boolean isCoinValid(String userInput) {
        if (Util.isCancel(userInput))
        {
            setCanceled();
            return true;
        }

        unsetCanceled();

        if (COIN_TYPE.hasValue(userInput))
        {
            Coin coin = new Coin(COIN_TYPE.getEnumFromString(userInput));
            m_credit.addCoin(coin);
            m_machine.insertCoin(coin);

            return true;
        }

        return false;
    }

    protected boolean isGiveChangePossible() {
        BigDecimal change = m_credit.getValue().subtract(m_currPrice);

        if (m_machine.isChangePossible(change))
        {
            return true;
        }

        setCanceled();
        return false;
    }
    
    protected String giveChange() {
        BigDecimal change = m_credit.getValue().subtract(m_currPrice);
        Vector<Coin> changeCoins = m_machine.giveChange(change);

        String coins = "";
        Enumeration<Coin> e = changeCoins.elements();

        while (e.hasMoreElements())
        {
            coins += (e.nextElement().getType().getStrValue() + ", ");
        }

        return (coins.isEmpty() ? coins : coins.substring(0, coins.length() - 2));
    }

    protected void setCanceled() {
        m_cenceled = true;
    }

    protected boolean isCanceled() {
        return m_cenceled;
    }

    protected void unsetCanceled() {
        m_cenceled = false;
    }

    protected void setCurrShelveNbr(int shelveNbr) {
        m_currShelveNbr = shelveNbr;
    }

    protected int getCurrShelveNbr() {
        return m_currShelveNbr;
    }

    protected IShelve getCurrShelve() {
        return m_shelves.get(m_currShelveNbr);
    }

    protected Vector<IProduct> getCurrShelveProducts() {
        return m_shelves.get(m_currShelveNbr).getProducts();
    }

    protected IProduct removeProduct() {
        return m_shelves.get(m_currShelveNbr).removeProduct();
    }

    protected boolean isCurrShelveEmpty() {
        return m_shelves.get(m_currShelveNbr).getProducts().isEmpty();
    }
}