package com.amazingnumbers.service;

import java.math.BigInteger;

public class NumberPropertyChecker {

    public static boolean isEven(BigInteger number) {
        return number.mod(BigInteger.TWO).equals(BigInteger.ZERO);
    }

    public static boolean isOdd(BigInteger number) {
        return !number.mod(BigInteger.TWO).equals(BigInteger.ZERO);
    }

    public static boolean isBuzz(BigInteger number) {
        return number.mod(BigInteger.TEN).equals(BigInteger.valueOf(7)) ||
                number.mod(BigInteger.valueOf(7)).equals(BigInteger.ZERO);
    }

    public static boolean isDuck(BigInteger number) {
        return number.compareTo(BigInteger.ZERO) > 0 &&
                (number.mod(BigInteger.TEN).equals(BigInteger.ZERO) || isDuck(number.divide(BigInteger.TEN)));
    }

    public static boolean isPalindromic(BigInteger number) {
        return number.toString().equals(new StringBuilder(number.toString()).reverse().toString());
    }
}
