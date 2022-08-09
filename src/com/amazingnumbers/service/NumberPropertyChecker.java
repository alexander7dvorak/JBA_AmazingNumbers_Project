package com.amazingnumbers.service;


public class NumberPropertyChecker {

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(long number) {
        return number % 2 != 0;
    }

    public static boolean isBuzz(long number) {
        return number % 10 == 7 ||
                number % 7 == 0;
    }

    public static boolean isDuck(long number) {
        return number > 0 &&
                (number % 10 == 0 || isDuck(number / 10));
    }

    public static boolean isPalindromic(long number) {
        return String.valueOf(number).equals(new StringBuilder(String.valueOf(number)).reverse().toString());
    }

    public static boolean isGapful(long number) {
        String numberString = String.valueOf(number);
        return String.valueOf(number).length() >= 3 &&
                number % (Long.parseLong(numberString.charAt(0) +
                        String.valueOf(numberString.charAt(numberString.length() - 1)))
                ) == 0;
    }

    public static boolean isSpy(long number) {
        String numberString = String.valueOf(number);
        Integer[] digits = new Integer[numberString.length()];
        long sumDigits = 0;
        long productDigits = 1;
        for (int i = 0; i < digits.length; i++) {
            digits[i] = Integer.parseInt(numberString.substring(i, i + 1));
            sumDigits += digits[i];
            productDigits *= digits[i];
        }
        return sumDigits == productDigits;
    }
}
