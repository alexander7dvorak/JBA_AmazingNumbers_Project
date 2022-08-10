package com.amazingnumbers.util;


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
        long sumDigits = 0;
        long productDigits = 1;
        for (int i = 0; i < numberString.length(); i++) {
            int digit = numberString.charAt(i) - '0';
            sumDigits += digit;
            productDigits *= digit;
        }
        return sumDigits == productDigits;
    }

    public static boolean isSquare(long number) {
        return Math.sqrt(number) % 1 == 0;
    }

    public static boolean isSunny(long number) {
        return Math.sqrt(number + 1) % 1 == 0;
    }

    public static boolean isJumping(long number) {
        boolean output = true;
        String numberString = String.valueOf(number);
        for (int i = 1; i < numberString.length(); i++) {
            if (Math.abs((int) (numberString.charAt(i - 1)) - (int) (numberString.charAt(i))) != 1) {
                output = false;
                break;
            }
        }
        return output;
    }

    public static boolean isHappy(long number) {
        long result = checkHappyNumber(number);
        while (result != 1 && result != 4) {
            result = checkHappyNumber(result);
        }
        return result == 1;
    }

    private static long checkHappyNumber(long number) {
        long sum = 0;
        int digit;
        String numberString = String.valueOf(number);
        for (int i = 0; i < numberString.length(); i++) {
            digit = numberString.charAt(i) - '0';
            sum += (long) digit * digit;
        }
        return sum;
    }
}
