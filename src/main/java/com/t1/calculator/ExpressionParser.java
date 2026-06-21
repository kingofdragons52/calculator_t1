package main.java.com.t1.calculator;

import java.util.*;

public class ExpressionParser {

    public static double evaluate(String expression) {
        expression = expression.trim();
        if (expression.isEmpty()) {
            return 0.0;
        }

        List<String> tokens = tokenize(expression);
        List<String> postfix = shuntingYard(tokens);
        return calculatePostfix(postfix);
    }

    private static List<String> tokenize(String expr) {
        expr = expr.replace("×", "*");
        List<String> tokens = new ArrayList<>();
        int i = 0;

        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (c == '√') {
                tokens.add(String.valueOf(c));
                i++;
            } else if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                tokens.add(sb.toString());
            } else if (c == '-') {
                if (tokens.isEmpty() || isOperator(tokens.get(tokens.size() - 1)) || tokens.get(tokens.size() - 1).equals("(")) {
                    tokens.add("u-");
                } else {
                    tokens.add("-");
                }
                i++;
            } else {
                tokens.add(String.valueOf(c));
                i++;
            }
        }
        return tokens;
    }

    private static List<String> shuntingYard(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isFunction(token) || token.equals("u-")) {
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                if (!stack.isEmpty() && (isFunction(stack.peek()) || stack.peek().equals("u-"))) {
                    output.add(stack.pop());
                }
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && (isOperator(stack.peek()) || stack.peek().equals("u-"))
                        && getPrecedence(stack.peek()) >= getPrecedence(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output;
    }

    private static double calculatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new RuntimeException("Некорректное выражение");
                }
                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        stack.push(a / b);
                        break;
                }
            } else if (token.equals("u-")) {
                if (stack.isEmpty()) {
                    throw new RuntimeException("Ошибка унарного минуса");
                }
                stack.push(-stack.pop());
            } else if (isFunction(token)) {
                if (stack.isEmpty()) {
                    throw new RuntimeException("Некорректное выражение для функции");
                }
                double target = stack.pop();
                if (token.equals("√")) {
                    if (target < 0) {
                        throw new ArithmeticException("Корень из отрицательного числа");
                    }
                    stack.push(Math.sqrt(target));
                }
            }
        }
        return stack.isEmpty() ? 0.0 : stack.pop();
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static boolean isFunction(String token) {
        return token.equals("√");
    }

    private static int getPrecedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) return 1;
        if (operator.equals("*") || operator.equals("/")) return 2;
        if (operator.equals("u-")) return 3;
        return -1;
    }
}