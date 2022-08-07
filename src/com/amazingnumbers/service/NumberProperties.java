package com.amazingnumbers.service;

import java.util.ArrayList;
import java.util.List;

public class NumberProperties {
    private final List<Boolean> properties;
    private final int number;

    public NumberProperties(int number) {
        this.number = number;
        this.properties = new ArrayList<>(5);
        this.properties.add(NumberPropertyChecker.isEven(number));
        this.properties.add(NumberPropertyChecker.isOdd(number));
        this.properties.add(NumberPropertyChecker.isBuzz(number));
        this.properties.add(NumberPropertyChecker.isDuck(number));
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return "Properties of %d\n".formatted(number) +
                "        even: %b\n".formatted(properties.get(0)) +
                "         odd: %b\n".formatted(properties.get(1)) +
                "        buzz: %b\n".formatted(properties.get(2)) +
                "        duck: %b\n".formatted(properties.get(3));
    }
}
