package com.t1.calculator;

public class ExpressionParser {
    public static double evaluate(String expression) {
        expression = expression.trim();

        if (expression.isEmpty()) {
            return 0.0;
        }
        return parseExp(expression);
    }

    public static double parseExp(String exp) {                // + и -
        exp = exp.trim();
        if (exp.startsWith("-")) {
            return -parseExp(exp.substring(1));
        }
        for (int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);

            if (ch == '+') {
                double left = parseExp(exp.substring(0, i));
                double right = parseTerm(exp.substring(i + 1));
                return left + right;
            }

            if (ch == '-') {
                double left = parseExp(exp.substring(0, i));
                double right = parseTerm(exp.substring(i + 1));
                return left - right;
            }
        }
        return parseTerm(exp);
    }

    public static double parseTerm(String exp) {                   // * и /
        exp = exp.trim();
        for (int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);

            if (ch == '*') {
                double left = parseExp(exp.substring(0, i));
                double right = parseTerm(exp.substring(i + 1));
                return left * right;
            }

            if (ch == '/') {
                double left = parseExp(exp.substring(0, i));
                double right = parseTerm(exp.substring(i + 1));
                if (right == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                return left / right;
            }
        }
        return parseFactor(exp);
    }

    public static double parseFactor(String exp) {           // скобки, унарный минус
        exp = exp.trim();
        if (exp.startsWith("-")) {
            return -parseExp(exp.substring(1, exp.length() - 1));
        }

        if (exp.startsWith("(") && exp.endsWith(")")) {
            return parseExp(exp.substring(1, exp.length() -1));
        }
        try {
            return Double.parseDouble(exp);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Неверное число: " + exp);
        }
    }

}