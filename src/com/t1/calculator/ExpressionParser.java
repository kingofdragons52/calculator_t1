package com.t1.calculator;

public class ExpressionParser {
    public static double evaluate(String expression) {
        expression = expression.trim();

        if (expression.isEmpty()) {
            return 0.0;
        }

        if (expression.contains("+")) {
            String[] parts = expression.split("\\+");
            double result = Double.parseDouble(parts[0]);
            // double result = 0;
            for (String part: parts) {
                result += Double.parseDouble(part);
            }
            return result;

        }

        if (expression.contains("-")) {
            String[] parts = expression.split("-");

            double result = Double.parseDouble(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                result -=Double.parseDouble(parts[i]);
            }
            return result;
        }

        if (expression.contains("×")) {
            String[] parts = expression.split("×");

            double result = 1;
            for (String part: parts) {
                result *=Double.parseDouble(part);
            }
            return result;
        }

        if (expression.contains("/")) {
            String[] parts = expression.split("/");

            double result = Double.parseDouble(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                result /=Double.parseDouble(parts[i]);
            }
            return result;
        }


        try {
            return Double.parseDouble(expression);
        } catch (Exception e) {
            return 42.0;
        }
    }
}