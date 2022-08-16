package com.amazingnumbers.service;

import com.amazingnumbers.model.NumberProperties;
import com.amazingnumbers.util.Stopper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
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
    private static long firstInputNumber = 1;

    @PostConstruct
    public static void run() {
        printWelcomeInfo();
        while (firstInputNumber != 0) {
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
        long secondInputNumber;
        try {
            if (numberOfTokens == 0) {
                Stopper.printWrongFirstParameter();
            } else if (numberOfTokens == 1) {
                firstInputNumber = Long.parseLong(st.nextToken());
                printInfo(firstInputNumber);
            } else if (numberOfTokens == 2) {
                firstInputNumber = Long.parseLong(st.nextToken());
                secondInputNumber = Integer.parseInt(st.nextToken());
                printInfo(firstInputNumber, secondInputNumber, new String[]{});
            } else {
                firstInputNumber = Long.parseLong(st.nextToken());
                secondInputNumber = Integer.parseInt(st.nextToken());
                String[] properties = new String[numberOfTokens - 2];
                int counter = 0;
                while (st.hasMoreElements()) {
                    properties[counter++] = st.nextToken().toUpperCase();
                }
                properties = new HashSet<>(Arrays.asList(properties)).toArray(new String[0]);
                printInfo(firstInputNumber, secondInputNumber, properties);
            }
        } catch (NumberFormatException e) {
            Stopper.printWrongRequestFormat();
        }
        System.out.println();
    }

    private static void printInfo(long number) {
        if (number == 0) {
            Stopper.printGoodbye();
        } else if (number < 0) {
            Stopper.printWrongFirstParameter();
        } else {
            System.out.println(new NumberProperties(number));
        }
    }

    private static void printInfo(long firstNumber, long secondNumber, String[] properties) {
        if (Stopper.isStop(properties)) {
            return;
        }

        if (secondNumber < 1) {
            Stopper.printWrongSecondParameter();
        } else {
            if (firstNumber == 0) {
                Stopper.printGoodbye();
            } else if (firstNumber < 0) {
                Stopper.printWrongFirstParameter();
            } else {
                for (long i = firstNumber, j = firstNumber; i < firstNumber + secondNumber; j++) {
                    String output = new NumberProperties(j).toShortString(properties);
                    if (output.length() > 0) {
                        System.out.println(output);
                        i++;
                    }
                }
            }
        }
    }
}
