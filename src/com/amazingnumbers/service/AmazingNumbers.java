package com.amazingnumbers.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Scanner;

@Service
public class AmazingNumbers {
    private static final Scanner scanner = new Scanner(System.in);
    private static BigInteger inputNumber = BigInteger.ONE;

    @PostConstruct
    public static void start() {
        printWelcomeInfo();
        while (!inputNumber.equals(BigInteger.ZERO)) {
            printUserPrompt();
            printInfo(inputNumber);
        }
    }

    private static void printWelcomeInfo() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private static void printUserPrompt() {
        System.out.println("Enter a request: ");
        inputNumber = scanner.nextBigInteger();
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
}
