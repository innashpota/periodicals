package com.inna.shpota.periodicals.util;

import java.math.BigDecimal;

public class Assert {
    public static void notEmpty(String str, String message) {
        if ("".equals(str) || str == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isPositive(int number, String message) {
        if (number <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isPositive(long number, String message) {
        if (number <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isPositive(BigDecimal number, String message) {
        if (number.signum() != 1) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}