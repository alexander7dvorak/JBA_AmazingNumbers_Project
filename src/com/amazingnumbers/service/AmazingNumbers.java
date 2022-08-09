package com.amazingnumbers.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

@Service
public class AmazingNumbers {
    private static final Scanner scanner = new Scanner(System.in);
    private static BigInteger inputNumber = BigInteger.ONE;

    @PostConstruct
    public static void start() {
        printWelcomeInfo();
        while (!inputNumber.equals(BigInteger.ZERO)) {
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
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private static void printUserPrompt() {
        System.out.println("Enter a request: ");
        String input = scanner.nextLine();
        StringTokenizer st = new StringTokenizer(input, " ");
        final int numberOfTokens = st.countTokens();
        try {
            if (numberOfTokens == 2) {
                inputNumber = new BigInteger(st.nextToken());
                int secondInputNumber = Integer.parseInt(st.nextToken());
                printInfo(inputNumber, secondInputNumber);
            } else if (numberOfTokens == 1) {
                inputNumber = new BigInteger(st.nextToken());
                printInfo(inputNumber);
            } else {
                System.out.println("The first parameter should be a natural number or zero.");
            }
        } catch (NumberFormatException e) {
            System.out.println("First and second parameters should be natural numbers or zeros.");
        }
        System.out.println();
    }

    private static void printInfo(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            System.out.println("Goodbye!");
        } else if (number.compareTo(BigInteger.ZERO) < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else {
            System.out.println(new NumberProperties(number));
        }
        System.out.println();
    }

    private static void printInfo(BigInteger number, int n) {
        if (n < 1) {
            System.out.println("second parameter should be a natural number.");
        } else {
            if (number.equals(BigInteger.ZERO)) {
                System.out.println("Goodbye!");
            } else if (number.compareTo(BigInteger.ZERO) < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else {
                for (BigInteger i = number; i.compareTo(number.add(BigInteger.valueOf(n))) < 0; i = i.add(BigInteger.ONE)) {
                    System.out.println(new NumberProperties(i).toShortString());
                }
            }
        }
        System.out.println();
    }
}