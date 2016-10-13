package tdd.vendingMachine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tdd.vendingMachine.software.CreditSoftwareTest;
import tdd.vendingMachine.software.MachineSoftwareTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    CreditSoftwareTest.class,
    MachineSoftwareTest.class
})

public class SoftwareTestSuite {

}