package com.amazingnumbers.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Service
public class AmazingNumbers {
    @PostConstruct
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        int inputNumber;
        System.out.println("Enter a natural number:");
        inputNumber = scanner.nextInt();
        printInfo(new NumberProperties(inputNumber));
    }

    private static void printInfo(NumberProperties properties) {
        if (properties.getNumber() < 1) {
            System.out.println("This number is not natural!");
        } else {
            System.out.println(properties);
        }
    }
}
