package com.inna.shpota.periodicals.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class AssertTest {
    private String message = "Error message";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void notEmptyGivenNullString() {
        String str = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.notEmpty(str, message);
    }

    @Test
    public void notEmptyGivenEmpryString() {
        String str = "";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.notEmpty(str, message);
    }

    @Test
    public void isPositiveInt() {
        int number = -4;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.isPositive(number, message);
    }

    @Test
    public void isPositiveLong() {
        long number = -4;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.isPositive(number, message);
    }

    @Test
    public void isPositiveBigDecimal() {
        BigDecimal number = new BigDecimal("-2.00");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.isPositive(number, message);
    }

    @Test
    public void notNull() {
        Object object = null;
        String message = "Error message";
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(message);

        Assert.notNull(object, message);
    }
}