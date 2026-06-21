package main.java.com.t1.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator extends JFrame {

    private JButton[] digitbuttons;
    private JTextField text;
    private JButton bPlus, bMinus, bMultiply, bDivide,
            bReciprocal, bSquare, bSqrt, bpoint,
            bEquals, bBackspace, bracketRight,
            bracketLeft, clear, percent,
            sin, cos, log, degree, e, pi;

    public Calculator(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        String courses[] = { "Обычный","Инженерный"};
        JComboBox c = new JComboBox(courses);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 1, 1));
        panel.setBackground(Color.black);

        digitbuttons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            digitbuttons[i] = new JButton(String.valueOf(i));
            int digit = i;

            digitbuttons[i].addActionListener(e -> {
                String current = text.getText();
                if (current.equals("0")) {
                    text.setText(String.valueOf(digit));
                } else {
                    text.setText(current + digit);
                }
                text.requestFocusInWindow();
            });
            digitbuttons[i].setBackground(Color.LIGHT_GRAY);
        }

        bPlus = new JButton("+");
        bMinus = new JButton("-");
        bMultiply = new JButton("×");
        bDivide = new JButton("/");
        bReciprocal = new JButton("1/x");
        bSquare = new JButton("x²");
        bSqrt = new JButton("√");
        bpoint = new JButton(".");
        bEquals = new JButton("=");
        bBackspace = new JButton("⌫");
        bracketRight = new JButton("(");
        bracketLeft = new JButton(")");
        percent = new JButton("%");
        clear = new JButton("C");
        sin = new JButton("sin");
        cos = new JButton("cos");
        log = new JButton("log");
        degree = new JButton("x^y");
        e = new JButton("e");
        pi = new JButton("π");

        JButton[] operatorButtons = {bPlus, bMinus, bMultiply, bDivide, bReciprocal, bSquare, bSqrt, bpoint, bEquals, bBackspace, bracketLeft, bracketRight, percent, clear, sin, cos, log, degree, e, pi };

        bPlus.addActionListener(e -> {
            String current = text.getText();
            text.setText(current + "+");
            text.requestFocusInWindow();
        });

        bMinus.addActionListener(e -> {
            String current = text.getText();
            text.setText(current + "-");
            text.requestFocusInWindow();
        });

        bMultiply.addActionListener(e -> {
            String current = text.getText();
            text.setText(current + "×");
            text.requestFocusInWindow();
        });

        bDivide.addActionListener(e -> {
            String current = text.getText();
            text.setText(current + "/");
            text.requestFocusInWindow();
        });

        bSquare.addActionListener(e -> {
            String current = text.getText();
            text.setText(current + "²");
            text.requestFocusInWindow();
        });

        bSqrt.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText("√(");
            } else {
                text.setText(current + "√(");
            }
            text.requestFocusInWindow();
        });

        bReciprocal.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText("1/(");
            } else {
                text.setText(current + "1/(");
            }
            text.requestFocusInWindow();
        });

        bpoint.addActionListener(e -> {
            String current = text.getText();
            text.setText(current + ".");
            text.requestFocusInWindow();
        });

        bracketRight.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText("(");
            } else {
                text.setText(current + "(");
            }
            text.requestFocusInWindow();
        });

        bracketLeft.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText(")");
            } else {
                text.setText(current + ")");
            }
            text.requestFocusInWindow();
        });

        clear.addActionListener(e -> {
            text.setText("0");
            text.requestFocusInWindow();
        });

        for (JButton button : operatorButtons) {
            button.setBackground(Color.LIGHT_GRAY);
        }

        panel.add(percent);
        panel.add(bracketRight);
        panel.add(bracketLeft);
        panel.add(clear);

        panel.add(bReciprocal);
        panel.add(bSquare);
        panel.add(bSqrt);
        panel.add(bBackspace);

        panel.add(digitbuttons[1]);
        panel.add(digitbuttons[2]);
        panel.add(digitbuttons[3]);
        panel.add(bPlus);

        panel.add(digitbuttons[4]);
        panel.add(digitbuttons[5]);
        panel.add(digitbuttons[6]);
        panel.add(bMinus);

        panel.add(digitbuttons[7]);
        panel.add(digitbuttons[8]);
        panel.add(digitbuttons[9]);
        panel.add(bMultiply);

        panel.add(bpoint);
        panel.add(digitbuttons[0]);
        panel.add(bEquals);
        panel.add(bDivide);

        text = new JTextField("0");
        text.setHorizontalAlignment(JTextField.RIGHT);

        Font font = new Font("Arial", Font.BOLD, 36);
        text.setForeground(Color.WHITE);
        text.setFont(font);
        text.setPreferredSize(new Dimension(390, 90));
        text.setBackground(Color.DARK_GRAY);

        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performCalculation();
                }
            }
        });

        this.add(text, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> text.requestFocusInWindow());
    }

    private void performCalculation() {
        String expression = text.getText();
        try {
            double result = ExpressionParser.evaluate(expression);
            text.setText(String.valueOf(result));
        } catch (Exception ex) {
            text.setText("Ошибка");
        }
    }
}