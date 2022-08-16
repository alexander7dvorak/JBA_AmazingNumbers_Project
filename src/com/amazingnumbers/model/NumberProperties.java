package com.amazingnumbers.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberProperties {
    private static final int NUMBER_OF_SPACES = 12;
    private final long number;
    private final HashMap<String, Boolean> properties = new HashMap<>();

    private StringBuilder outputBuilderShort;
    private StringBuilder outputBuilderLong;
    private boolean firstTrueProperty = true;
    private int counter = 0;

    public NumberProperties(long number) {
        this.number = number;
        for (Property p : Property.values()) {
            this.properties.put(p.name(), p.getPredicate().test(number));
        }
    }

    public String toString() {
        if (outputBuilderLong == null) {
            fillOutputBuilder();
        }
        return outputBuilderLong.toString();
    }

    private void fillOutputBuilder() {
        outputBuilderLong = new StringBuilder();
        outputBuilderLong.append("Properties of %d".formatted(number));
        for (String propertyName : properties.keySet()) {
            addPropertyToLongString(propertyName);
        }
    }

    private void addPropertyToLongString(String propertyName) {
        outputBuilderLong.append("\n");
        outputBuilderLong.append(createNSpacesString(NUMBER_OF_SPACES - propertyName.length()));
        outputBuilderLong.append(propertyName.toLowerCase());
        outputBuilderLong.append(": %b".formatted(properties.get(propertyName)));
    }

    private String createNSpacesString(int n) {
        return " ".repeat(Math.max(0, n));
    }

    public String toShortString(String[] requestProperties) {
        if (outputBuilderShort == null) {
            fillOutputBuilder(requestProperties);
        }
        return requestProperties.length == 0 || counter == requestProperties.length ?
                outputBuilderShort.toString() :
                "";
    }

    private void fillOutputBuilder(String[] requestProperties) {
        outputBuilderShort = new StringBuilder();
        Set<String> requestPropertiesSet = new HashSet<>(List.of(requestProperties));
        for (String propertyName : properties.keySet()) {
            addPropertyToShortString(propertyName, requestPropertiesSet);
        }
    }

    private void addPropertyToShortString(String propertyName, Set<String> requestPropertiesSet) {
        if (properties.get(propertyName)) {
            if (!firstTrueProperty) {
                outputBuilderShort.append(", ");
            } else {
                outputBuilderShort.append(number);
                outputBuilderShort.append(" is ");
            }
            firstTrueProperty = false;
            outputBuilderShort.append(propertyName.toLowerCase());
            if (requestPropertiesSet.contains(propertyName.toUpperCase())) {
                counter++;
            }
        } else if (requestPropertiesSet.contains("-" + propertyName.toUpperCase())) {
            counter++;
        }
    }
}