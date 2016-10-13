package tdd.vendingMachine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tdd.vendingMachine.machine.CoinBoxTest;
import tdd.vendingMachine.machine.ShelveTest;
import tdd.vendingMachine.machine.ShelveTypeTest;
import tdd.vendingMachine.machine.VendingMachineTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    CoinBoxTest.class,
    ShelveTest.class,
    ShelveTypeTest.class,
    VendingMachineTest.class
})

public class MachineTestSuite {

}