package com.amazingnumbers.model;

import com.amazingnumbers.util.NumberPropertyChecker;

import java.util.function.Predicate;

public enum Property {
    EVEN(NumberPropertyChecker::isEven),
    ODD(NumberPropertyChecker::isOdd),
    BUZZ(NumberPropertyChecker::isBuzz),
    DUCK(NumberPropertyChecker::isDuck),
    PALINDROMIC(NumberPropertyChecker::isPalindromic),
    GAPFUL(NumberPropertyChecker::isGapful),
    SPY(NumberPropertyChecker::isSpy),
    SQUARE(NumberPropertyChecker::isSquare),
    SUNNY(NumberPropertyChecker::isSunny),
    JUMPING(NumberPropertyChecker::isJumping),
    HAPPY(NumberPropertyChecker::isHappy),
    SAD(Predicate.not(HAPPY.predicate));
    final private Predicate<Long> predicate;

    Property(Predicate<Long> isPropertyOfSomeNumberMethod) {
        this.predicate = isPropertyOfSomeNumberMethod;
    }

    public Predicate<Long> getPredicate() {
        return this.predicate;
    }
}
