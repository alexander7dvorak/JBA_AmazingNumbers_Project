package com.amazingnumbers.util;

import com.amazingnumbers.model.NumberProperties;

public class NumberPropertyPrinter {
    public static void printInfo(long number) {
        if (number == 0) {
            Stopper.printGoodbye();
        } else if (number < 0) {
            Stopper.printWrongFirstParameter();
        } else {
            System.out.println(new NumberProperties(number));
        }
    }

    public static void printInfo(long firstNumber, long secondNumber, String[] properties) {
        if (Stopper.isStop(properties)) {
            return;
        }

        if (secondNumber < 1) {
            Stopper.printWrongSecondParameter();
        } else {
            if (firstNumber == 0) {
                Stopper.printGoodbye();
            } else if (firstNumber < 0) {
                Stopper.printWrongFirstParameter();
            } else {
                printEligibleNumbers(firstNumber, secondNumber, properties);
            }
        }
    }

    private static void printEligibleNumbers(long firstNumber, long secondNumber, String[] properties) {
        for (long i = firstNumber, j = firstNumber; i < firstNumber + secondNumber; j++) {
            String output = new NumberProperties(j).toShortString(properties);
            if (output.length() > 0) {
                System.out.println(output);
                i++;
            }
        }
    }
}
