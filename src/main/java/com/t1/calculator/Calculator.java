package com.t1.calculator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends JFrame {

    private JTextField text;
    private JButton[] digitButtons;
    private Map<String, JButton> opButtons = new HashMap<>();

    public Calculator() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 400);
        setLocationRelativeTo(null);
        setTitle("Calculator");

        text = new JTextField("0");
        text.setFont(new Font("Arial", Font.BOLD, 36));
        text.setForeground(Color.WHITE);
        text.setBackground(Color.DARK_GRAY);
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setPreferredSize(new Dimension(320, 80));

        // МОМЕНТ 2: Слушаем нажатие Enter в текстовом поле
        text.addActionListener(e -> executeCalculation());

        this.add(text, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 2, 2));
        panel.setBackground(Color.BLACK);

        digitButtons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            final int digit = i;
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].setBackground(Color.LIGHT_GRAY);
            digitButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            digitButtons[i].addActionListener(e -> {
                String current = text.getText();
                text.setText(current.equals("0") ? String.valueOf(digit) : current + digit);
            });
        }

        String[] simpleOps = {"+", "-", "×", "/", "(", ")", "."};
        for (String op : simpleOps) {
            JButton btn = new JButton(op);
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(e -> {
                String current = text.getText();
                text.setText(current.equals("0") ? op : current + op);
            });
            opButtons.put(op, btn);
        }

        JButton clearBtn = new JButton("C");
        clearBtn.setBackground(Color.RED);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 18));
        clearBtn.addActionListener(e -> text.setText("0"));

        JButton backspaceBtn = new JButton("⌫");
        backspaceBtn.addActionListener(e -> {
            String current = text.getText();
            if (current.length() > 0 && !current.equals("0")) {
                String next = current.substring(0, current.length() - 1);
                text.setText(next.isEmpty() ? "0" : next);
            }
        });

        JButton equalsBtn = new JButton("=");
        equalsBtn.setBackground(Color.ORANGE);
        equalsBtn.setFont(new Font("Arial", Font.BOLD, 18));
        equalsBtn.addActionListener(e -> executeCalculation());

        panel.add(opButtons.get("("));   panel.add(opButtons.get(")"));   panel.add(backspaceBtn);       panel.add(clearBtn);
        panel.add(digitButtons[1]);      panel.add(digitButtons[2]);      panel.add(digitButtons[3]);      panel.add(opButtons.get("+"));
        panel.add(digitButtons[4]);      panel.add(digitButtons[5]);      panel.add(digitButtons[6]);      panel.add(opButtons.get("-"));
        panel.add(digitButtons[7]);      panel.add(digitButtons[8]);      panel.add(digitButtons[9]);      panel.add(opButtons.get("×"));
        panel.add(opButtons.get("."));   panel.add(digitButtons[0]);      panel.add(equalsBtn);            panel.add(opButtons.get("/"));

        this.add(panel, BorderLayout.CENTER);
    }

    private void executeCalculation() {
        try {
            String expression = text.getText().replace("×", "*");
            double result = RecursiveParser.evaluate(expression);
            text.setText(String.valueOf(result));
        } catch (Exception ex) {
            text.setText("Ошибка");
        }
    }
}