package com.t1.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionParserTest {

    @Test
    public void testPlus() {
        String expression = "2+3";
        double result = RecursiveParser.evaluate(expression);
        assertEquals(5.0, result, 0.001);
    }

    @Test
    public void testMinus() {
        String expression = "2-3";
        double result = RecursiveParser.evaluate(expression);
        assertEquals(-1.0, result, 0.001);
    }

    @Test
    public void testMultiply() {
        String expression = "2*3";
        double result = RecursiveParser.evaluate(expression);
        assertEquals(6.0, result, 0.001);
    }

    @Test
    public void testDivide() {
        String expression = "6/3";
        double result = RecursiveParser.evaluate(expression);
        assertEquals(2.0, result, 0.001);
    }


    @Test
    public void testOperatorPriority() {
        String expression = "2+2*3";
        double result = RecursiveParser.evaluate(expression);
        assertEquals(8.0, result, 0.001);
    }

    @Test
    public void testParentheses() {
        String expression = "(2+2)*3";
        double result = RecursiveParser.evaluate(expression);
        assertEquals(12.0, result, 0.001);
    }

    @Test
    public void testDecimalNumbers() {
        String expression = "2.5+1.5";
        double result = RecursiveParser.evaluate(expression);
        assertEquals(4.0, result, 0.001);
    }

    @Test
    public void testDivideByZero() {
        String expression = "5/0";
        assertThrows(ArithmeticException.class, () -> {
            RecursiveParser.evaluate(expression);
        });
    }

    @Test
    public void testInvalidCharacters() {
        String expression = "2+abc";
        assertThrows(RuntimeException.class, () -> {
            RecursiveParser.evaluate(expression);
        });
    }
}