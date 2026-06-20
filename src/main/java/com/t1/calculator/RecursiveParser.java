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
            if      (eat('*')) x *= parseFactor(); // Умножение
            else if (eat('/')) {
                double val = parseFactor();
                if (val == 0) throw new ArithmeticException("Деление на ноль");
                x /= val; // Деление
            }
            else return x;
        }
    }

    private double parseFactor() {
        if (eat('(')) {
            double x = parseExpression();
            eat(')');
            return x;
        }

        int startPos = this.pos;
        if ((ch >= '0' && ch <= '9') || ch == '.') {
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            return Double.parseDouble(str.substring(startPos, this.pos));
        }

        throw new RuntimeException("Неожиданный символ: " + (char)ch);
    }
}