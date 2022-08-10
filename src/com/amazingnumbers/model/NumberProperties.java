package com.amazingnumbers.model;

import com.amazingnumbers.util.NumberPropertyChecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NumberProperties {
    private final long number;
    private final HashMap<String, Boolean> properties = new HashMap<>();
    private static final int NUMBER_OF_SPACES = 12;
    private StringBuilder outputSB;
    private boolean firstTrue = true;
    private int counter = 0;

    public NumberProperties(long number) {
        this.number = number;
        this.properties.put(Property.EVEN.toString(), NumberPropertyChecker.isEven(number));
        this.properties.put(Property.ODD.toString(), NumberPropertyChecker.isOdd(number));
        this.properties.put(Property.BUZZ.toString(), NumberPropertyChecker.isBuzz(number));
        this.properties.put(Property.DUCK.toString(), NumberPropertyChecker.isDuck(number));
        this.properties.put(Property.PALINDROMIC.toString(), NumberPropertyChecker.isPalindromic(number));
        this.properties.put(Property.GAPFUL.toString(), NumberPropertyChecker.isGapful(number));
        this.properties.put(Property.SPY.toString(), NumberPropertyChecker.isSpy(number));
        this.properties.put(Property.SQUARE.toString(), NumberPropertyChecker.isSquare(number));
        this.properties.put(Property.SUNNY.toString(), NumberPropertyChecker.isSunny(number));
        this.properties.put(Property.JUMPING.toString(), NumberPropertyChecker.isJumping(number));
        this.properties.put(Property.HAPPY.toString(), NumberPropertyChecker.isHappy(number));
        this.properties.put(Property.SAD.toString(), !this.properties.get(Property.HAPPY.toString()));
    }

    public String toString() {
        outputSB = new StringBuilder();
        outputSB.append("Properties of %d\n".formatted(number));
        for (String propertyName : properties.keySet()) {
            outputSB.append(createNSpacesString(NUMBER_OF_SPACES - propertyName.length()));
            outputSB.append(propertyName.toLowerCase());
            outputSB.append(": %b\n".formatted(properties.get(propertyName)));
        }
        return outputSB.toString();
    }

    private String createNSpacesString(int n) {
        return " ".repeat(Math.max(0, n));
    }

    public String toShortString(String[] requestProperties) {
        outputSB = new StringBuilder();
        for (String propertyName : properties.keySet()) {
            addPropertyToShortString(propertyName, Arrays.asList(requestProperties));
        }
        return requestProperties.length == 0 || counter == requestProperties.length ? outputSB.toString() : "";
    }

    private void addPropertyToShortString(String propertyName, List<String> requestPropertiesList) {
        if (properties.get(propertyName)) {
            if (!firstTrue) {
                outputSB.append(", ");
            } else {
                outputSB.append(number);
                outputSB.append(" is ");
            }
            firstTrue = false;
            outputSB.append(propertyName.toLowerCase());
            if (requestPropertiesList.contains(propertyName.toUpperCase())) {
                counter++;
            }
        } else if (requestPropertiesList.contains("-" + propertyName.toUpperCase())) {
            counter++;
        }
    }
}