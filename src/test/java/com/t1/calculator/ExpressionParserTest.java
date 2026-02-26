package test.java.com.t1.calculator;

import com.t1.calculator.ExpressionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionParserTest {
    @Test
    public void testPlus() {
        // Arrange (подготовка)
        String expression = "2+3";

        // Act (действие)
        double result = ExpressionParser.evaluate(expression);

        // Assert (проверка)
        assertEquals(5, result, 0.001);
    }

    @Test
    public void testMinus() {
        // Arrange (подготовка)
        String expression = "2-3";

        // Act (действие)
        double result = ExpressionParser.evaluate(expression);

        // Assert (проверка)
        assertEquals(-1, result, 0.001);
    }

    @Test
    public void testMultiply() {
        // Arrange (подготовка)
        String expression = "2×3";

        // Act (действие)
        double result = ExpressionParser.evaluate(expression);

        // Assert (проверка)
        assertEquals(6, result, 0.001);

    }

    @Test
    public void testDivide() {
        // Arrange (подготовка)
        String expression = "6/3";

        // Act (действие)
        double result = ExpressionParser.evaluate(expression);

        // Assert (проверка)
        assertEquals(2, result, 0.001);


    }

    @Test
    public void testDivideExpetion() {
        String expression =  "5/0";
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            double result = ExpressionParser.evaluate(expression);
        });
        Assertions.assertEquals("Деление на ноль", exception.getMessage());
    }

    @Test
    public void testComplex() {
        // Arrange (подготовка)
        String expression = "2/4+3";

        // Act (действие)
        double result = ExpressionParser.evaluate(expression);

        // Assert (проверка)
        assertEquals(3.5, result, 0.001);


    }

}
