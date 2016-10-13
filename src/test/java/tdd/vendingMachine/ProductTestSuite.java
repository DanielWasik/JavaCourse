package tdd.vendingMachine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tdd.vendingMachine.product.ProductTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    ProductTest.class
})

public class ProductTestSuite {

}