package tdd.testRunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import tdd.vendingMachine.CoinTestSuite;
import tdd.vendingMachine.DisplayTestSuite;
import tdd.vendingMachine.ExceptionTestSuite;
import tdd.vendingMachine.MachineTestSuite;
import tdd.vendingMachine.MessagesTestSuite;
import tdd.vendingMachine.ProductTestSuite;
import tdd.vendingMachine.SoftwareTestSuite;
import tdd.vendingMachine.UtilTestSuite;

public class TestRunner 
{
    public static void main(String[] args) 
    {
        Result result = JUnitCore.runClasses(
                CoinTestSuite.class,
                DisplayTestSuite.class,
                ExceptionTestSuite.class,
                MachineTestSuite.class,
                MessagesTestSuite.class,
                ProductTestSuite.class,
                SoftwareTestSuite.class,
                UtilTestSuite.class);

        for (Failure failure : result.getFailures())
        {
            System.out.println("FAILED TEST: " + failure.toString());
        }

        System.out.println("TEST SUITS RESULT: " + result.wasSuccessful());
    }
} 