package com.amazingnumbers.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
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
            if (numberOfTokens == 2) {
                inputNumber = Long.parseLong(st.nextToken());
                secondInputNumber = Integer.parseInt(st.nextToken());
                printInfo(inputNumber, secondInputNumber, new String[]{});
            } else if (numberOfTokens == 1) {
                inputNumber = Long.parseLong(st.nextToken());
                printInfo(inputNumber);
            } else if (numberOfTokens == 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else {
                inputNumber = Long.parseLong(st.nextToken());
                secondInputNumber = Integer.parseInt(st.nextToken());
                String[] properties = new String[numberOfTokens - 2];
                int counter = 0;
                while (st.hasMoreElements()) {
                    properties[counter++] = st.nextToken().toUpperCase();
                }
                List<String> propertiesList = List.of(properties);
                if (propertiesList.contains("ODD") && propertiesList.contains("EVEN")) {
                    System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]");
                    System.out.println("There are no numbers with these properties.");
                    return;
                } else if (propertiesList.contains("DUCK") && propertiesList.contains("SPY")) {
                    System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]");
                    System.out.println("There are no numbers with these properties.");
                    return;
                } else if (propertiesList.contains("SUNNY") && propertiesList.contains("SQUARE")) {
                    System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]");
                    System.out.println("There are no numbers with these properties.");
                    return;
                } else {
                    printInfo(inputNumber, secondInputNumber, properties);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("First and second parameters should be natural numbers or zeros.");
        }
        System.out.println();
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
        List<String> wrongProperties = new ArrayList<>();
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
                    break;
                default:
                    wrongProperties.add(property);
            }
        }
        if (wrongProperties.size() > 1) {
            System.out.print("The properties [");
            System.out.print(wrongProperties.get(0));
            for (int i = 1; i < wrongProperties.size(); i++) {
                System.out.print(", " + wrongProperties.get(i));
            }
            System.out.println("] are wrong.");
            System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
            System.out.println();
            return;
        } else if (wrongProperties.size() == 1) {
            System.out.println("The property [" + wrongProperties.get(0) + "] is wrong.");
            System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
            System.out.println();
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
}