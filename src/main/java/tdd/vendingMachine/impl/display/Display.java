package tdd.vendingMachine.impl.display;

import tdd.vendingMachine.IDisplay;

import java.util.Scanner;

public class Display implements IDisplay {

    private Scanner scanInput;

    public Display() {
        scanInput = new Scanner(System.in);
    }

    @Override
    public void displayMessage(String msg) {
        System.out.println(msg);
    };

    @Override
    public void displayInputMessage(String msg) {
        System.out.print(msg);
    };

    @Override
    public String getUserInput() {
        return scanInput.nextLine();
    };

    @Override
    public void clearScreen() {
        System.out.println("#############################################################");
    }
}