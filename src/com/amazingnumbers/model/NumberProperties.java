package com.amazingnumbers.model;

import com.amazingnumbers.util.NumberPropertyChecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NumberProperties {
    private final HashMap<String, Boolean> properties = new HashMap<>();
    private final long number;
    private boolean firstTrue = true;
    private final StringBuilder outputSB = new StringBuilder();
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
        return "Properties of %d\n".formatted(number) +
                "        even: %b\n".formatted(properties.get(Property.EVEN.toString())) +
                "         odd: %b\n".formatted(properties.get(Property.ODD.toString())) +
                "        buzz: %b\n".formatted(properties.get(Property.BUZZ.toString())) +
                "        duck: %b\n".formatted(properties.get(Property.DUCK.toString())) +
                " palindromic: %b\n".formatted(properties.get(Property.PALINDROMIC.toString())) +
                "      gapful: %b\n".formatted(properties.get(Property.GAPFUL.toString())) +
                "         spy: %b\n".formatted(properties.get(Property.SPY.toString())) +
                "      square: %b\n".formatted(properties.get(Property.SQUARE.toString())) +
                "       sunny: %b\n".formatted(properties.get(Property.SUNNY.toString())) +
                "     jumping: %b\n".formatted(properties.get(Property.JUMPING.toString())) +
                "       happy: %b\n".formatted(properties.get(Property.HAPPY.toString())) +
                "         sad: %b\n".formatted(properties.get(Property.SAD.toString()));
    }

    public String toShortString(String[] requestProperties) {
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
            outputSB.append(propertyName.toLowerCase());
            if (requestPropertiesList.contains(propertyName.toUpperCase())) {
                counter++;
            }
            firstTrue = false;
        } else if (requestPropertiesList.contains("-" + propertyName.toUpperCase())) {
            counter++;
        }
        requestPropertiesList.remove(propertyName.toUpperCase());
    }
}