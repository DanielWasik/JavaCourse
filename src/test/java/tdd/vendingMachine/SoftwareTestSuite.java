package tdd.vendingMachine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tdd.vendingMachine.software.CreditSoftwareTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    CreditSoftwareTest.class
})

public class SoftwareTestSuite {

}