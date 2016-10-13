package tdd.vendingMachine.impl.software;

import java.math.BigDecimal;

import tdd.vendingMachine.IDisplay;
import tdd.vendingMachine.impl.coin.COIN_TYPE;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.messages.INFORMATION;
import tdd.vendingMachine.impl.messages.WARNING;
import tdd.vendingMachine.impl.util.Util;

public class DisplaySoftware {

    private IDisplay m_display;
    private String m_lastUserInput;

    public DisplaySoftware(IDisplay display)
            throws IllegalNullArgumentException {
        Util.checkNotNull(display, "display");
        m_display = display;
    }

    protected void showShelves(String shelveNbr) {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.WELCOME.get());
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.ALLOWED_SHELVES.get() + shelveNbr);
    }

    protected void showWrongShelveSelected(String shelveNbr) {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + WARNING.WRONG_SHELVE.get() + INFORMATION.ALLOWED_SHELVES.get() + shelveNbr);
    }

    protected void showWrongCoinInserted() {
        m_display.displayMessage(INFORMATION.MONEY_DISPENCER.get() + WARNING.WRONG_COIN.get() + getLastUserInput());
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
    }

    protected void showSoldOutShelveSelected(String shelveNbr) {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + WARNING.NO_PRODUCT.get() + INFORMATION.ALLOWED_SHELVES.get() + shelveNbr);
    }

    protected void showCredit(String currCredit) {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.USER_CREDIT.get() + currCredit + INFORMATION.CURRENCY.get());
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
    }

    protected void showShelveInput() {
        m_display.displayInputMessage(INFORMATION.DISPLAY.get() + INFORMATION.SHELVE_INPUT.get());
    }

    protected void showCoinInput() {
        m_display.displayInputMessage(INFORMATION.MONEY_INTAKE.get() + INFORMATION.COIN_INPUT.get());
    }

    protected String getUserInput() {
        setLastUserInput(m_display.getUserInput());
        return getLastUserInput();
    }

    protected void clearScreen() {
        m_display.clearScreen();
    }

    protected void setLastUserInput(String userInput) {
        m_lastUserInput = userInput;
    }

    protected String getLastUserInput() {
        return m_lastUserInput;
    }

    protected void showShelveHeader(int shelveNbr) {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.SELECTED_SHELVE.get() + shelveNbr);
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.DASH_LINE.get());
    }

    protected void showProduct(int nbr, String desc, BigDecimal price) {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.PRODUCT_NBR.get() +
                nbr + INFORMATION.PRODUCT_DESC.get() + desc + INFORMATION.PRODUCT_PRICE.get() +
                price + INFORMATION.CURRENCY.get());
    }

    protected void showMoneyToAdd(String moneyToAdd) {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.MONAY_TO_ADD.get() +
                moneyToAdd + INFORMATION.CURRENCY.get());
    }

    protected void showCancel() {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.CANCEL.get());
    }

    protected void showAllowedCoins() {
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.ALLOWED_COINS.get() + COIN_TYPE.getAll());
    }

    protected void showTransactionCanceled() {
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.TRANSACTION_CANCELED.get());
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
    }

    protected void showTransactionCanceled(String credit) {
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.TRANSACTION_CANCELED_MONEY.get());
        m_display.displayMessage(INFORMATION.MONEY_DISPENCER.get()  + credit);
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
    }

    protected void showGetChangeImpossible() {
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
        m_display.displayMessage(INFORMATION.DISPLAY.get() + WARNING.NO_CHANGE.get());
    }

    protected void showGetBoughtProduct(String product) {
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.TAKE_PRODUCT.get());
        m_display.displayMessage(INFORMATION.PRODUCT_DISPENCER.get() + product);
    }

    protected void showGetChange(String change) {
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.TAKE_CHANGE.get());
        m_display.displayMessage(INFORMATION.MONEY_DISPENCER.get() + change);
    }

    protected void showThanksForShopping() {
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
        m_display.displayMessage(INFORMATION.DISPLAY.get() + INFORMATION.BYE.get());
        m_display.displayMessage(INFORMATION.EMPTY_LINE.get());
    }
}