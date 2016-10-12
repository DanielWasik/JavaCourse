package tdd.vendingMachine.impl.util;

import java.util.Vector;

import tdd.vendingMachine.impl.exceptions.IllegalEmptyArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalNullArgumentException;
import tdd.vendingMachine.impl.exceptions.IllegalOutOfRangeArgumentException;
import tdd.vendingMachine.impl.messages.ERROR;
import tdd.vendingMachine.impl.messages.INFORMATION;

public class Util {

    private Util() {

    };

    public static boolean isInteger(String number) {
        if (number == null || number.isEmpty())
        {
            return false;
        }

        try {
            Integer.parseInt(number);
        }
        catch (NumberFormatException ex)
        {
            return false;
        }

        return true;
    }

    public static boolean isCancel(String cancel) {
        if (cancel == null || cancel.isEmpty())
        {
            return false;
        }

        return (cancel.compareTo(INFORMATION.CANCEL_INPUT.get()) == 0 ? true : false);
    }

    public static <T> T checkNotNull(T param, String arg)
            throws IllegalNullArgumentException {
        if (param == null) 
        {
            throw new IllegalNullArgumentException(arg + ERROR.NULL_ARGUMENT.get());
        }

        return param;
    }

    public static int checkIsPositive(int param, String arg)
            throws IllegalNullArgumentException, IllegalOutOfRangeArgumentException {
        Util.checkNotNull(param, arg);

        if (param <= 0)
        {
            throw new IllegalOutOfRangeArgumentException(arg + ERROR.OUT_OF_RANGE_ARGUMENT.get() + param);
        }

        return param;
    }

    public static String checkIsEmpty(String param, String arg)
            throws IllegalNullArgumentException, IllegalEmptyArgumentException {
        Util.checkNotNull(param, arg);

        if (param.isEmpty())
        {
            throw new IllegalEmptyArgumentException(arg + ERROR.EMPTY_ARGUMENT.get());
        }

        return param;
    }

    public static <T> Vector<T> checkIsEmpty(Vector<T> param, String arg)
            throws IllegalNullArgumentException, IllegalEmptyArgumentException {
        Util.checkNotNull(param, arg);

        if (param.isEmpty())
        {
            throw new IllegalEmptyArgumentException(arg + ERROR.EMPTY_ARGUMENT.get());
        }

        return param;
    }
}