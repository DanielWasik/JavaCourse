package tdd.vendingMachine;

public interface IDisplay {

    public abstract void displayMessage(String msg);

    public abstract void displayInputMessage(String msg);

    public abstract String getUserInput();

    public abstract void clearScreen();
}