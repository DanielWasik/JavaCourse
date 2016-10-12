package tdd.vendingMachine.impl.messages;

import tdd.vendingMachine.IMessage;

public enum ERROR implements IMessage {

    EMPTY_ARGUMENT(": param can't be Empty."),
    NULL_ARGUMENT(": param can't be NULL."),
    OUT_OF_RANGE_ARGUMENT(": param is out of range. Value: "),
    WRONG_CLASS_ARGUMENT(": param is of wrong class."),
    WRONG_INIT_CONFIG("Vending machine was not configured properly."),
    WRONG_PRODUCT("Shelve can contain products only of the same type!");

    private String m_message;

    private ERROR(String message) {
        this.m_message = message;
    }

    public String get() {
        return m_message;
    }
}