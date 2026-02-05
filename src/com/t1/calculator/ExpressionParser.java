package com.t1.calculator;

public class ExpressionParser {
    public static double evaluate(String expression) {
        expression = expression.trim();

        // Если пустая строка
        if (expression.isEmpty()) {
            return 0.0;
        }

        // Пробуем разобрать выражение
        if (expression.contains("+")) {
            // Пример: "2+3" → разделяем по "+"
            String[] parts = expression.split("\\+"); // \\+ потому что + спецсимвол

            try {
                double a = Double.parseDouble(parts[0]); // "2" → 2.0
                double b = Double.parseDouble(parts[1]); // "3" → 3.0
                return a + b; // 5.0
            } catch (Exception e) {
                return 42.0; // если ошибка
            }
        }

        if (expression.contains("-")) {
            String[] parts = expression.split("-");

            try {
                double a = Double.parseDouble(parts[0]);
                double b = Double.parseDouble(parts[1]);
                return a - b;
            } catch (Exception e) {
                return 52.0;
            }
        }

        if (expression.contains("*")) {
            String[] parts = expression.split("\\*");

            try {
                double a = Double.parseDouble(parts[0]);
                double b = Double.parseDouble(parts[1]);
                return a * b;
            } catch (Exception e) {
                return 42.0;
            }
        }

        if (expression.contains("/")) {
            String[] parts = expression.split("/");

            try {
                double a = Double.parseDouble(parts[0]);
                double b = Double.parseDouble(parts[1]);
                return a / b;
            } catch (Exception e) {
                return 42.0;
            }
        }


        // Если не нашли "+", пробуем как просто число
        try {
            return Double.parseDouble(expression); // "123" → 123.0
        } catch (Exception e) {
            return 42.0; // заглушка
        }
    }
}