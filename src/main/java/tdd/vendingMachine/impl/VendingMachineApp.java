package tdd.vendingMachine.impl;

import java.util.Vector;

import tdd.vendingMachine.PRODUCT_TYPE;
import tdd.vendingMachine.impl.coin.COIN_TYPE;
import tdd.vendingMachine.impl.coin.Coin;
import tdd.vendingMachine.impl.display.Display;
import tdd.vendingMachine.impl.machine.VendingMachine;
import tdd.vendingMachine.impl.messages.ERROR;
import tdd.vendingMachine.impl.product.Product;
import tdd.vendingMachine.impl.software.ControllerSoftware;

public class VendingMachineApp {

    public static void main(String[] args) {

        try
        {
            Display display = new Display();
            VendingMachine vendingMachine = new VendingMachine(10);
            ControllerSoftware software = new ControllerSoftware(vendingMachine, display);

            Vector<Product> bars = Product.createProducts(PRODUCT_TYPE.BAR, 5);
            Vector<Product> chips = Product.createProducts(PRODUCT_TYPE.CHIPS, 3);
            Vector<Product> colas = Product.createProducts(PRODUCT_TYPE.COLA, 7);
            Vector<Product> gums = Product.createProducts(PRODUCT_TYPE.GUM, 6);
            Vector<Product> juis = Product.createProducts(PRODUCT_TYPE.JUICE, 2);
            Vector<Product> waters = Product.createProducts(PRODUCT_TYPE.WATER, 1);

            Vector<Coin> fiveDollarCoins = Coin.createCoins(COIN_TYPE.FIVE_DOLAR, 20);
            Vector<Coin> twoDollarCoins = Coin.createCoins(COIN_TYPE.TWO_DOLAR, 30);
            Vector<Coin> oneDollarCoins = Coin.createCoins(COIN_TYPE.ONE_DOLAR, 40);
            Vector<Coin> fiftyCentCoins = Coin.createCoins(COIN_TYPE.FIFTY_CENT, 50);
            Vector<Coin> twnetyCentCoins = Coin.createCoins(COIN_TYPE.TWENTY_CENT, 60);
            Vector<Coin> tenCentCoins = Coin.createCoins(COIN_TYPE.TEN_CENT, 70);

            vendingMachine.addProducts(1, bars);
            vendingMachine.addProducts(2, chips);
            vendingMachine.addProducts(3, colas);
            vendingMachine.addProducts(4, gums);
            vendingMachine.addProducts(5, juis);
            vendingMachine.addProducts(6, waters);
            vendingMachine.addProducts(7, waters);
            vendingMachine.addProducts(8, gums);
            vendingMachine.addProducts(9, juis);

            vendingMachine.addCoins(fiveDollarCoins);
            vendingMachine.addCoins(twoDollarCoins);
            vendingMachine.addCoins(oneDollarCoins);
            vendingMachine.addCoins(fiftyCentCoins);
            vendingMachine.addCoins(twnetyCentCoins);
            vendingMachine.addCoins(tenCentCoins);

            software.Start();
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println(ERROR.WRONG_INIT_CONFIG.get());
            ex.printStackTrace();
        }
    }
}