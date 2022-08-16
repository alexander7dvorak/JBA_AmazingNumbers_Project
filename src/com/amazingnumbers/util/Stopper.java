package com.amazingnumbers.util;

import com.amazingnumbers.model.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stopper {

    public static void printWrongRequestFormat() {
        System.out.println("First and second parameters should be natural numbers or zeros.");
    }

    public static void printWrongFirstParameter() {
        System.out.println("The First parameter should be a natural number or zero.");
    }

    public static void printWrongSecondParameter() {
        System.out.println("The Second parameter should be a natural number.");
    }

    public static void printGoodbye() {
        System.out.println("Goodbye!");
    }

    public static boolean isStop(String[] properties) {
        if (isStopDueToWrongProperties(properties) || isStopDueToMutuallyExclusiveProperties(properties)) {
            System.out.printf("Available properties: %s\n", Arrays.toString(Property.values()));
            return true;
        }
        return false;
    }

    private static boolean isStopDueToWrongProperties(String[] properties) {
        List<String> wrongProperties = new ArrayList<>();
        addWrongProperties(properties, wrongProperties);
        if (wrongProperties.size() > 1) {
            System.out.print("The properties [");
            System.out.print(wrongProperties.get(0));
            for (int i = 1; i < wrongProperties.size(); i++) {
                System.out.print(", " + wrongProperties.get(i));
            }
            System.out.println("] are wrong.");
            return true;
        } else if (wrongProperties.size() == 1) {
            System.out.println("The property [" + wrongProperties.get(0) + "] is wrong.");
            return true;
        }
        return false;
    }

    private static void addWrongProperties(String[] properties, List<String> wrongProperties) {
        Set<String> rightProperties = new HashSet<>();
        for (Property p : Property.values()) {
            rightProperties.add(p.toString());
            rightProperties.add("-" + p);
        }
        for (String property : properties) {
            if (!rightProperties.contains(property)) {
                wrongProperties.add(property);
            }
        }
    }

    private static boolean isStopDueToMutuallyExclusiveProperties(String[] properties) {
        HashSet<String> propertiesSet = new HashSet<>(List.of(properties));
        for (String s : propertiesSet) {
            if (s.startsWith("-")
                    && isMutuallyExclusiveProperties(false, s.substring(1), s, propertiesSet)) {
                return true;
            }
        }
        return isMutuallyExclusiveProperties(false, propertiesSet) ||
                isMutuallyExclusiveProperties(true, propertiesSet);
    }

    private static boolean isMutuallyExclusiveProperties(boolean minus, String propertyA, String propertyB, HashSet<String> propertiesSet) {
        String minusString = minus ? "-" : "";
        if (propertiesSet.contains("%s%s".formatted(minusString, propertyA.toUpperCase()))
                && propertiesSet.contains("%s%s".formatted(minusString, propertyB.toUpperCase()))) {
            System.out.printf("The request contains mutually exclusive properties: [%s%s, %s%s]\n", minusString, propertyA, minusString, propertyB);
            System.out.println("There are no numbers with these properties.");
            return true;
        } else {
            return false;
        }
    }

    private static boolean isMutuallyExclusiveProperties(boolean minus, HashSet<String> propertiesSet) {
        return isMutuallyExclusiveProperties(minus, Property.ODD.name(), Property.EVEN.name(), propertiesSet)
                || isMutuallyExclusiveProperties(minus, Property.DUCK.name(), Property.SPY.name(), propertiesSet)
                || isMutuallyExclusiveProperties(minus, Property.SUNNY.name(), Property.SQUARE.name(), propertiesSet)
                || isMutuallyExclusiveProperties(minus, Property.HAPPY.name(), Property.SAD.name(), propertiesSet);
    }
}
