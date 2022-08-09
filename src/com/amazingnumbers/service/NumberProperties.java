package com.amazingnumbers.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumberProperties {
    private final List<Boolean> properties;
    private final BigInteger number;

    public NumberProperties(BigInteger number) {
        this.number = number;
        this.properties = new ArrayList<>(5);
        this.properties.add(NumberPropertyChecker.isEven(number));
        this.properties.add(NumberPropertyChecker.isOdd(number));
        this.properties.add(NumberPropertyChecker.isBuzz(number));
        this.properties.add(NumberPropertyChecker.isDuck(number));
        this.properties.add(NumberPropertyChecker.isPalindromic(number));
        this.properties.add(NumberPropertyChecker.isGapful(number));
    }

    public String toString() {
        return "Properties of %d\n".formatted(number) +
                "        even: %b\n".formatted(properties.get(0)) +
                "         odd: %b\n".formatted(properties.get(1)) +
                "        buzz: %b\n".formatted(properties.get(2)) +
                "        duck: %b\n".formatted(properties.get(3)) +
                " palindromic: %b\n".formatted(properties.get(4)) +
                "      gapful: %b\n".formatted(properties.get(5));
    }

    public String toShortString() {
        StringBuilder outputSB = new StringBuilder(number.toString() + " is ");
        boolean firstTrue = true;
        if (properties.get(2)) {
            outputSB.append("buzz");
            firstTrue = false;
        }
        if (properties.get(3)) {
            if (!firstTrue) {
                outputSB.append(", ");
            }
            outputSB.append("duck");
            firstTrue = false;
        }
        if (properties.get(4)) {
            if (!firstTrue) {
                outputSB.append(", ");
            }
            outputSB.append("palindromic");
            firstTrue = false;
        }
        if (properties.get(5)) {
            if (!firstTrue) {
                outputSB.append(", ");
            }
            outputSB.append("gapful");
            firstTrue = false;
        }
        if (properties.get(0)) {
            if (!firstTrue) {
                outputSB.append(", ");
            }
            outputSB.append("even");
            firstTrue = false;
        }
        if (properties.get(1)) {
            if (!firstTrue) {
                outputSB.append(", ");
            }
            outputSB.append("odd");
        }
        return outputSB.toString();
    }
}