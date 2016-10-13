package tdd.vendingMachine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tdd.vendingMachine.coin.CoinTest;
import tdd.vendingMachine.coin.CoinTypeTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    CoinTest.class,
    CoinTypeTest.class
})

public class CoinTestSuite {

}