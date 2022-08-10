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
import java.util.StringTokenizer;

@Service
public class AmazingNumbers {
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
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list: ");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private static void printUserPrompt() {
        System.out.println("Enter a request: ");
        String input = scanner.nextLine();
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
            System.out.println();
        } else {
            System.out.println(new NumberProperties(number));
        }
    }

    private static void printInfo(long number, int n, String[] properties) {
        if (abortDueToWrongProperties(properties) || abortDueToMutuallyExclusiveProperties(properties)) {
            return;
        }
        if (n < 1) {
            System.out.println("The Second parameter should be a natural number.");
        } else {
            if (number == 0) {
                System.out.println("Goodbye!");
            } else if (number < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println();
            } else {
                System.out.println();
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
            System.out.printf("Available properties: %s\n", Arrays.toString(Property.values()));
            System.out.println();
            return true;
        } else if (wrongProperties.size() == 1) {
            System.out.println("The property [" + wrongProperties.get(0) + "] is wrong.");
            System.out.printf("Available properties: %s\n", Arrays.toString(Property.values()));
            System.out.println();
            return true;
        }
        return false;
    }

    private static void addWrongProperties(String[] properties, List<String> wrongProperties) {
        for (String property : properties) {
            switch (property) {
                case "SPY":
                case "GAPFUL":
                case "PALINDROMIC":
                case "DUCK":
                case "BUZZ":
                case "ODD":
                case "EVEN":
                case "SQUARE":
                case "SUNNY":
                case "JUMPING":
                case "HAPPY":
                case "SAD":
                case "-SPY":
                case "-GAPFUL":
                case "-PALINDROMIC":
                case "-DUCK":
                case "-BUZZ":
                case "-ODD":
                case "-EVEN":
                case "-SQUARE":
                case "-SUNNY":
                case "-JUMPING":
                case "-HAPPY":
                case "-SAD":
                    break;
                default:
                    wrongProperties.add(property);
            }
        }
    }

    private static boolean abortDueToMutuallyExclusiveProperties(String[] properties) {
        List<String> propertiesList = List.of(properties);
        for (String s : propertiesList) {
            if (s.startsWith("-")) {
                if (propertiesList.contains(s.substring(1))) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", s.substring(1), s);
                    return true;
                }
            }
        }
        return isRequestContainingConstConditionsOfMutuallyExclusiveProperties("", propertiesList) ||
                isRequestContainingConstConditionsOfMutuallyExclusiveProperties("-", propertiesList);
    }

    private static boolean isRequestContainingConstConditionsOfMutuallyExclusiveProperties(String minus, List<String> propertiesList) {
        if (propertiesList.contains("%sODD".formatted(minus)) && propertiesList.contains("%sEVEN".formatted(minus))) {
            System.out.printf("The request contains mutually exclusive properties: [%sODD, %sEVEN]\n", minus, minus);
            System.out.println("There are no numbers with these properties.");
            return true;
        } else if (propertiesList.contains("%sDUCK".formatted(minus)) && propertiesList.contains("%sSPY".formatted(minus))) {
            System.out.printf("The request contains mutually exclusive properties: [%sDUCK, %sSPY]\n", minus, minus);
            System.out.println("There are no numbers with these properties.");
            return true;
        } else if (propertiesList.contains("%sSUNNY".formatted(minus)) && propertiesList.contains("%sSQUARE".formatted(minus))) {
            System.out.printf("The request contains mutually exclusive properties: [%sSUNNY, %sSQUARE]\n", minus, minus);
            System.out.println("There are no numbers with these properties.");
            return true;
        } else if (propertiesList.contains("%sHAPPY".formatted(minus)) && propertiesList.contains("%sSAD".formatted(minus))) {
            System.out.printf("The request contains mutually exclusive properties: [%sHAPPY, %sSAD]\n", minus, minus);
            System.out.println("There are no numbers with these properties.");
            return true;
        }
        return false;
    }
}
