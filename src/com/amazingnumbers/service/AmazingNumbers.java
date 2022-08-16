package com.amazingnumbers.service;

import com.amazingnumbers.model.NumberProperties;
import com.amazingnumbers.model.Property;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

@Service
public class AmazingNumbers {
    private static final String welcomeInfo = """
            Welcome to Amazing Numbers!

                    Supported requests:
                    - enter a natural number to know its properties;
                    - enter two natural numbers to obtain the properties of the list:
                      * the first parameter represents a starting number;
                      * the second parameter shows how many consecutive numbers are to be printed;
                    - two natural numbers and properties to search for;
                    - a property preceded by minus must not be present in numbers;
                    - separate the parameters with one space;
                    - enter 0 to exit.
                    """;
    private static final Scanner scanner = new Scanner(System.in);
    private static long inputNumber = 1;

    @PostConstruct
    public static void run() {
        printWelcomeInfo();
        while (inputNumber != 0) {
            printUserPrompt();
        }
    }

    private static void printWelcomeInfo() {
        System.out.println(welcomeInfo);
    }

    private static void printUserPrompt() {
        System.out.print("Enter a request: ");
        String input = scanner.nextLine();
        System.out.println();

        processUserPrompt(input);
    }

    private static void processUserPrompt(String input) {
        StringTokenizer st = new StringTokenizer(input, " ");
        final int numberOfTokens = st.countTokens();
        int secondInputNumber;
        try {
            if (numberOfTokens == 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println();
            } else if (numberOfTokens == 1) {
                inputNumber = Long.parseLong(st.nextToken());
                printInfo(inputNumber);
            } else if (numberOfTokens == 2) {
                inputNumber = Long.parseLong(st.nextToken());
                secondInputNumber = Integer.parseInt(st.nextToken());
                printInfo(inputNumber, secondInputNumber, new String[]{});
            } else {
                inputNumber = Long.parseLong(st.nextToken());
                secondInputNumber = Integer.parseInt(st.nextToken());
                String[] properties = new String[numberOfTokens - 2];
                int counter = 0;
                while (st.hasMoreElements()) {
                    properties[counter++] = st.nextToken().toUpperCase();
                }
                properties = new HashSet<>(Arrays.asList(properties)).toArray(new String[0]);
                printInfo(inputNumber, secondInputNumber, properties);
            }
        } catch (NumberFormatException e) {
            System.out.println("First and second parameters should be natural numbers or zeros.");
            System.out.println();
        }
    }

    private static void printInfo(long number) {
        if (number == 0) {
            System.out.println("Goodbye!");
        } else if (number < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else {
            System.out.println(new NumberProperties(number));
        }
        System.out.println();
    }

    private static void printInfo(long number, int n, String[] properties) {
        if (abort(properties)) {
            return;
        }

        if (n < 1) {
            System.out.println("The Second parameter should be a natural number.");
        } else {
            if (number == 0) {
                System.out.println("Goodbye!");
            } else if (number < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else {
                for (long i = number, j = number; i < number + n; j++) {
                    String output = new NumberProperties(j).toShortString(properties);
                    if (output.length() > 0) {
                        System.out.println(output);
                        i++;
                    }
                }
            }
        }
        System.out.println();
    }

    private static boolean abort(String[] properties) {
        if (abortDueToWrongProperties(properties) || abortDueToMutuallyExclusiveProperties(properties)) {
            System.out.printf("Available properties: %s\n", Arrays.toString(Property.values()));
            System.out.println();
            return true;
        }
        return false;
    }

    private static boolean abortDueToWrongProperties(String[] properties) {
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

    private static boolean abortDueToMutuallyExclusiveProperties(String[] properties) {
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

    private static boolean isMutuallyExclusiveProperties(boolean minus, HashSet<String> propertiesSet) {
        return isMutuallyExclusiveProperties(minus, Property.ODD.name(), Property.EVEN.name(), propertiesSet)
                || isMutuallyExclusiveProperties(minus, Property.DUCK.name(), Property.SPY.name(), propertiesSet)
                || isMutuallyExclusiveProperties(minus, Property.SUNNY.name(), Property.SQUARE.name(), propertiesSet)
                || isMutuallyExclusiveProperties(minus, Property.HAPPY.name(), Property.SAD.name(), propertiesSet);
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
}
