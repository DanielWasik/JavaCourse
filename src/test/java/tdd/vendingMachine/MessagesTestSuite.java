package tdd.vendingMachine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tdd.vendingMachine.messages.MessageTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    MessageTest.class
})

public class MessagesTestSuite {

}