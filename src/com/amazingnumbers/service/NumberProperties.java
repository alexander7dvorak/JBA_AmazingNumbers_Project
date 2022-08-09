package com.amazingnumbers.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberProperties {
    private final List<Boolean> properties;
    private final long number;
    private boolean firstTrue = true;
    private final StringBuilder outputSB = new StringBuilder();
    private int counter = 0;

    public NumberProperties(long number) {
        this.number = number;
        this.properties = new ArrayList<>(
                List.of(new Boolean[]{false, false, false, false, false, false, false, false, false, false}));
        this.properties.set(0, NumberPropertyChecker.isEven(number));
        this.properties.set(1, NumberPropertyChecker.isOdd(number));
        this.properties.set(2, NumberPropertyChecker.isBuzz(number));
        this.properties.set(3, NumberPropertyChecker.isDuck(number));
        this.properties.set(4, NumberPropertyChecker.isPalindromic(number));
        this.properties.set(5, NumberPropertyChecker.isGapful(number));
        this.properties.set(6, NumberPropertyChecker.isSpy(number));
        this.properties.set(7, NumberPropertyChecker.isSquare(number));
        this.properties.set(8, NumberPropertyChecker.isSunny(number));
        this.properties.set(9, NumberPropertyChecker.isJumping(number));
    }

    public String toString() {
        return "Properties of %d\n".formatted(number) +
                "        even: %b\n".formatted(properties.get(0)) +
                "         odd: %b\n".formatted(properties.get(1)) +
                "        buzz: %b\n".formatted(properties.get(2)) +
                "        duck: %b\n".formatted(properties.get(3)) +
                " palindromic: %b\n".formatted(properties.get(4)) +
                "      gapful: %b\n".formatted(properties.get(5)) +
                "         spy: %b\n".formatted(properties.get(6)) +
                "      square: %b\n".formatted(properties.get(7)) +
                "       sunny: %b\n".formatted(properties.get(8)) +
                "     jumping: %b\n".formatted(properties.get(9));
    }

    public String toShortString(String[] inputProperties) {
        List<String> propertiesList = Arrays.asList(inputProperties);
        addPropertyToShortString(2, "buzz", propertiesList);
        addPropertyToShortString(3, "duck", propertiesList);
        addPropertyToShortString(4, "palindromic", propertiesList);
        addPropertyToShortString(5, "gapful", propertiesList);
        addPropertyToShortString(6, "spy", propertiesList);
        addPropertyToShortString(7, "square", propertiesList);
        addPropertyToShortString(8, "sunny", propertiesList);
        addPropertyToShortString(9, "jumping", propertiesList);
        addPropertyToShortString(0, "even", propertiesList);
        addPropertyToShortString(1, "odd", propertiesList);
        String output = outputSB.toString();
        return inputProperties.length == 0 || counter == inputProperties.length ? output : "";
    }

    private void addPropertyToShortString(int index, String property, List<String> inputPropertiesList) {
        if (properties.get(index)) {
            if (!firstTrue) {
                outputSB.append(", ");
            } else {
                outputSB.append(number);
                outputSB.append(" is ");
            }
            outputSB.append(property.toLowerCase());
            if (inputPropertiesList.contains(property.toUpperCase())) {
                counter++;
            }
            firstTrue = false;
        }
    }
}