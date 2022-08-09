package com.amazingnumbers.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
                printInfo(inputNumber, secondInputNumber, properties);
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
        for (String property : properties) {
            switch (property) {
                case "SPY":
                case "GAPFUL":
                case "PALINDROMIC":
                case "DUCK":
                case "BUZZ":
                case "ODD":
                case "EVEN":
                    break;
                default:
                    System.out.printf("The property [%s] is wrong.%n", property);
                    System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]");
                    System.out.println();
                    return;
            }
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