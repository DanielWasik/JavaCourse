package tdd.vendingMachine.impl.messages;

import tdd.vendingMachine.IMessage;

public enum INFORMATION implements IMessage {

    ALLOWED_COINS("You can insert such coins: "),
    ALLOWED_SHELVES("You can select from shelves: "),
    BYE("Thank you for shooping with us."),
    CANCEL("To 'CANCEL' wite: C"),
    CANCEL_INPUT("C"),
    CHOOSE_PRODUCT("Please, choose product number: "),
    COIN_INPUT("Insert coin or CANCEL: "),
    CURRENCY("$"),
    DASH_LINE("--------------------------------------"),
    DISPLAY("DISPLAY: "),
    EMPTY_LINE(""),
    MONAY_TO_ADD("To buy please insert: "),
    MONEY_DISPENCER("MONEY_DISPENCER: "),
    MONEY_INTAKE("MONEY_INTAKE: "),
    PRODUCT_DESC(" PRODUCT: "),
    PRODUCT_DISPENCER("PRODUCT_DISPENCER: "),
    PRODUCT_NBR("#"), 
    PRODUCT_PRICE(" PRICE: "),
    SELECTED_SHELVE("            SHELVE NBR: "),
    SHELVE_INPUT("Select shelve : "),
    TAKE_CHANGE("Please, take your change."),
    TAKE_PRODUCT("Please, take your product."),
    TRANSACTION_CANCELED("Transaction has been canceled"),
    TRANSACTION_CANCELED_MONEY("Transaction has been canceled. Please, take back your money."),
    USER_CREDIT("Your credit is: "),
    WELCOME("Welcome. Please write shelve number to see product price...");

    private String m_message;

    private INFORMATION(String message) {
        this.m_message = message;
    }

    public String get() {
        return m_message;
    }
}