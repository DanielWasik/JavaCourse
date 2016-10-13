package tdd.vendingMachine.impl.software;

import java.util.Enumeration;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import tdd.vendingMachine.impl.software.DisplaySoftware;
import tdd.vendingMachine.IDisplay;
import tdd.vendingMachine.IProduct;
import tdd.vendingMachine.ISoftware;
import tdd.vendingMachine.IVendingMachine;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.util.Util;

public class ControllerSoftware implements ISoftware {

    private DisplaySoftware m_displaySoft;
    private MachineSoftware m_machineSoft;

    public ControllerSoftware(IVendingMachine machine, IDisplay display)
            throws IllegalNullArgumentException {
        Util.checkNotNull(machine, "machine");
        Util.checkNotNull(display, "display");

        m_machineSoft = new MachineSoftware(machine);
        m_displaySoft = new DisplaySoftware(display);
    }

    void showShelves() {
        m_displaySoft.showShelves(m_machineSoft.getShelvesNbr());

        if (!m_machineSoft.getSoldOut())
        {
            m_displaySoft.showCredit(m_machineSoft.getCreditAsString());
            m_displaySoft.showShelveInput();
        }
    }

    private void getUserShelveChoise() {
        String shelvesNbr = m_machineSoft.getShelvesNbr();
        String credit = m_machineSoft.getCreditAsString();

        while (m_machineSoft.isShelveNbrValid(m_displaySoft.getUserInput()) == false)
        {
            m_displaySoft.showWrongShelveSelected(shelvesNbr);
            m_displaySoft.showCredit(credit);
            m_displaySoft.showShelveInput();
        }

        if (m_machineSoft.isCurrShelveEmpty())
        {
            m_displaySoft.showWrongShelveSelected(shelvesNbr);
            m_displaySoft.showCredit(credit);
            m_displaySoft.showShelveInput();

            getUserShelveChoise();
        }

        m_displaySoft.clearScreen();
    }

    private void showShelveProducts() {
        int i = 0;
        Vector<IProduct> products = m_machineSoft.getCurrShelveProducts();
        Enumeration<IProduct> e = products.elements();

        m_displaySoft.showShelveHeader(m_machineSoft.getCurrShelveNbr());

        while (e.hasMoreElements())
        {
            IProduct prod = e.nextElement();
            m_displaySoft.showProduct(++i, prod.getType().getDesc(), prod.getPrice());
        }

        m_displaySoft.showCredit(m_machineSoft.getCreditAsString());
    }

    private void waitSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkIsCanceled() {
        if (m_machineSoft.isCanceled())
        {
            if (m_machineSoft.isCreditEmpty())
            {
                m_displaySoft.showTransactionCanceled();
            }
            else
            {
                m_displaySoft.showTransactionCanceled(m_machineSoft.getCreditCoins());
                m_machineSoft.cancelTransaction();
            }

            waitSeconds(3);
            m_displaySoft.clearScreen();
            Start();
        }
    }

    private void getUserCoinInput() {
        m_displaySoft.showMoneyToAdd(m_machineSoft.getAddMoneyAsString());
        m_displaySoft.showCancel();
        m_displaySoft.showAllowedCoins();
        m_displaySoft.showCoinInput();

        while (m_machineSoft.isCoinValid(m_displaySoft.getUserInput()) == false)
        {
            m_displaySoft.clearScreen();
            showShelveProducts();

            m_displaySoft.showWrongCoinInserted();
            m_displaySoft.showMoneyToAdd(m_machineSoft.getAddMoneyAsString());
            m_displaySoft.showCancel();
            m_displaySoft.showAllowedCoins();
            m_displaySoft.showCoinInput();
        }

        checkIsCanceled();
    }

    private void checkChangeCondition() {
        if (m_machineSoft.isAddMoneyNeeded())
        {
            return;
        }

        if (!m_machineSoft.isGiveChangePossible())
        {
            m_displaySoft.showGetChangeImpossible();
            checkIsCanceled();
        }
    }

    private void checkEnoughMoneyCondition() {
        if (m_machineSoft.isAddMoneyNeeded())
        {
            m_displaySoft.clearScreen();
            showShelveProducts();
            getUserCoinInput();
            checkBuyConditions();
        }
    }

    private void checkBuyConditions() {
        checkChangeCondition();
        checkEnoughMoneyCondition();
    }

    private void giveProduct() {
        m_displaySoft.clearScreen();
        m_displaySoft.showGetBoughtProduct(m_machineSoft.removeProduct().getType().getDesc());
    }

    private void giveChange() {
        if (m_machineSoft.isGiveChangeNeeded())
        {
            String change = m_machineSoft.giveChange();
            m_displaySoft.showGetChange(change);
        }

        m_machineSoft.resetCredit(); 
    }

    private void thanksForShopping() {
        waitSeconds(3);
        m_displaySoft.showThanksForShopping();

        waitSeconds(3);
        m_displaySoft.clearScreen();

        Start();
    }

    public void Start() {

        showShelves();

        getUserShelveChoise();

        showShelveProducts();

        getUserCoinInput();

        checkBuyConditions();

        giveProduct();

        giveChange();

        thanksForShopping();
    }
}