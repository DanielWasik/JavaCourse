package tdd.vendingMachine.impl.messages;

import tdd.vendingMachine.IMessage;

public enum WARNING implements IMessage {

    NO_CHANGE("Sorry, there is no enough coins to give you a change. Please, get back your money."),
    NO_PRODUCT("Sorry, this shelve is empty. You can choose other product."),
    NOT_ENOUGH_MONEY("Not enough money. Please press 'Cancel' to get your money back."),
    OUT_OF_SERVICE("Sorry. Machine Out of Service ..."),
    SOLD_OUT("Sorry, all products are SOLD OUT. Please, try again tomorrow."),
    WRONG_COIN("Wrong coin inserted! Get it back: "),
    WRONG_SHELVE("Try again. Invalid shelve number. ");

    private String m_message;

    private WARNING(String message) {
        this.m_message = message;
    }

    public String get() {
        return m_message;
    }
}