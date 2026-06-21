package test.java.com.t1.calculator;

import main.java.com.t1.calculator.ExpressionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionParserTest {

    @Test
    public void testPlus() {
        String expression = "2+3";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(5, result, 0.001);
    }

    @Test
    public void testMinus() {
        String expression = "2-3";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(-1, result, 0.001);
    }

    @Test
    public void testMultiply() {
        String expression = "2×3";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(6, result, 0.001);
    }

    @Test
    public void testDivide() {
        String expression = "6/3";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(2, result, 0.001);
    }

    @Test
    public void testDivideException() {
        String expression = "5/0";
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            ExpressionParser.evaluate(expression);
        });
        Assertions.assertEquals("Деление на ноль", exception.getMessage());
    }

    @Test
    public void testComplex() {
        String expression = "2/4+3";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(3.5, result, 0.001);
    }

    @Test
    public void testWithBrackets() {
        String expression = "(2+3)×4";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(20.0, result, 0.001);
    }

    @Test
    public void testNestedBrackets() {
        String expression = "((2+3)×2)-5";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(5.0, result, 0.001);
    }

    @Test
    public void testEmptyExpression() {
        assertEquals(0.0, ExpressionParser.evaluate(""), 0.001);
        assertEquals(0.0, ExpressionParser.evaluate("   "), 0.001);
    }

    @Test
    public void testZeroExpression() {
        assertEquals(0.0, ExpressionParser.evaluate("0"), 0.001);
    }

    @Test
    public void testDecimalNumbers() {
        String expression = "2.5+1.5";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(4.0, result, 0.001);
    }

    @Test
    public void testSpacesHandling() {
        String expression = " 10  /  2 + 3 ";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(8.0, result, 0.001);
    }

    @Test
    public void testSqrtExpression() {
        String expression = "√(2+2)";
        double result = ExpressionParser.evaluate(expression);
        assertEquals(2.0, result, 0.001);
    }
}