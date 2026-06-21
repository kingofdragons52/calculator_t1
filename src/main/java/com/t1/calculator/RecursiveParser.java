package com.t1.calculator;

public class RecursiveParser {
    private final String str;
    private int pos = -1;
    private int ch;

    private RecursiveParser(String str) {
        this.str = str;
    }

    private void nextChar() {
        ch = (++pos < str.length()) ? str.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    // Вспомогательный метод для парсинга текстовых функций (например, "1/x" или "x²")
    private boolean eatFunction(String name) {
        while (ch == ' ') nextChar();
        if (pos + name.length() <= str.length() && str.substring(pos, pos + name.length()).equals(name)) {
            pos += name.length() - 1;
            nextChar();
            return true;
        }
        return false;
    }

    public static double evaluate(String expression) {
        RecursiveParser parser = new RecursiveParser(expression);
        parser.nextChar();
        double x = parser.parseExpression();
        if (parser.pos < expression.length()) throw new RuntimeException("Неожиданный символ: " + (char)parser.ch);
        return x;
    }

    private double parseExpression() {
        double x = parseTerm();
        for (;;) {
            if      (eat('+')) x += parseTerm();
            else if (eat('-')) x -= parseTerm();
            else return x;
        }
    }

    private double parseTerm() {
        double x = parseFactor();
        for (;;) {
            if      (eat('*')) x *= parseFactor();
            else if (eat('/')) {
                double val = parseFactor();
                if (val == 0) throw new ArithmeticException("Деление на ноль");
                x /= val;
            }
            else return x;
        }
    }

    private double parseFactor() {
        if (eat('+')) return parseFactor();
        if (eat('-')) return -parseFactor();

        if (eat('(')) {
            double x = parseExpression();
            eat(')');
            return x;
        }

        // 1. Корень из числа/выражения
        if (eat('√')) {
            double x = parseFactor();
            if (x < 0) throw new ArithmeticException("Корень из отрицательного числа");
            return Math.sqrt(x);
        }

        // 2. Квадрат числа/выражения: x²(...)
        if (eatFunction("x²")) {
            double x = parseFactor();
            return x * x;
        }

        // 3. Обратное число: 1/x(...)
        if (eatFunction("1/x")) {
            double x = parseFactor();
            if (x == 0) throw new ArithmeticException("Деление на ноль при вычислении 1/x");
            return 1.0 / x;
        }

        // 4. Процент (деление на 100): %(...)
        if (eat('%')) {
            double x = parseFactor();
            return x / 100.0;
        }

        int startPos = this.pos;
        if ((ch >= '0' && ch <= '9') || ch == '.') {
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            return Double.parseDouble(str.substring(startPos, this.pos));
        }

        throw new RuntimeException("Неожиданный символ: " + (char)ch);
    }
}