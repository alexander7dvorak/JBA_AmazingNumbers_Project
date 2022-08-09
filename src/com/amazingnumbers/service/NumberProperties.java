package com.amazingnumbers.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberProperties {
    private final List<Boolean> properties;
    private final long number;

    public NumberProperties(long number) {
        this.number = number;
        this.properties = new ArrayList<>(List.of(new Boolean[]{false, false, false, false, false, false, false}));
        this.properties.set(0, NumberPropertyChecker.isEven(number));
        this.properties.set(1, NumberPropertyChecker.isOdd(number));
        this.properties.set(2, NumberPropertyChecker.isBuzz(number));
        this.properties.set(3, NumberPropertyChecker.isDuck(number));
        this.properties.set(4, NumberPropertyChecker.isPalindromic(number));
        this.properties.set(5, NumberPropertyChecker.isGapful(number));
        this.properties.set(6, NumberPropertyChecker.isSpy(number));
    }

    public String toString() {
        return "Properties of %d\n".formatted(number) +
                "        even: %b\n".formatted(properties.get(0)) +
                "         odd: %b\n".formatted(properties.get(1)) +
                "        buzz: %b\n".formatted(properties.get(2)) +
                "        duck: %b\n".formatted(properties.get(3)) +
                " palindromic: %b\n".formatted(properties.get(4)) +
                "      gapful: %b\n".formatted(properties.get(5)) +
                "         spy: %b\n".formatted(properties.get(6));
    }

    public String toShortString(String[] inputProperties) {
        StringBuilder outputSB = new StringBuilder();
        boolean firstTrue = true;
        List<String> propertiesList = Arrays.asList(inputProperties);
        int counter = 0;
        if (properties.get(2)) {
            outputSB.append(number);
            outputSB.append(" is ");
            outputSB.append("buzz");
            if (propertiesList.contains("BUZZ")) {
                counter++;
            }
            firstTrue = false;
        }
        if (properties.get(3)) {
            if (!firstTrue) {
                outputSB.append(", ");
            } else {
                outputSB.append(number);
                outputSB.append(" is ");
            }
            outputSB.append("duck");
            if (propertiesList.contains("DUCK")) {
                counter++;
            }
            firstTrue = false;
        }
        if (properties.get(4)) {
            if (!firstTrue) {
                outputSB.append(", ");
            } else {
                outputSB.append(number);
                outputSB.append(" is ");
            }
            outputSB.append("palindromic");
            if (propertiesList.contains("PALINDROMIC")) {
                counter++;
            }
            firstTrue = false;
        }
        if (properties.get(5)) {
            if (!firstTrue) {
                outputSB.append(", ");
            } else {
                outputSB.append(number);
                outputSB.append(" is ");
            }
            outputSB.append("gapful");
            if (propertiesList.contains("GAPFUL")) {
                counter++;
            }
            firstTrue = false;
        }
        if (properties.get(6)) {
            if (!firstTrue) {
                outputSB.append(", ");
            } else {
                outputSB.append(number);
                outputSB.append(" is ");
            }
            outputSB.append("spy");
            if (propertiesList.contains("SPY")) {
                counter++;
            }
            firstTrue = false;
        }
        if (properties.get(0)) {
            if (!firstTrue) {
                outputSB.append(", ");
            } else {
                outputSB.append(number);
                outputSB.append(" is ");
            }
            outputSB.append("even");
            if (propertiesList.contains("EVEN")) {
                counter++;
            }
            firstTrue = false;
        }
        if (properties.get(1)) {
            if (!firstTrue) {
                outputSB.append(", ");
            } else {
                outputSB.append(number);
                outputSB.append(" is ");
            }
            outputSB.append("odd");
            if (propertiesList.contains("ODD")) {
                counter++;
            }
        }
        String output = outputSB.toString();
        return inputProperties.length == 0 || counter == inputProperties.length ? output : "";
    }
}