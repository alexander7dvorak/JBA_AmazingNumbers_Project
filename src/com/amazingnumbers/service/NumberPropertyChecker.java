package com.amazingnumbers.service;

public class NumberPropertyChecker {

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    public static boolean isBuzz(int number) {
        return number % 10 == 7 || number % 7 == 0;
    }

    public static boolean isDuck(int number) {
        return number > 0 && (number % 10 == 0 || isDuck(number / 10));
    }
}
