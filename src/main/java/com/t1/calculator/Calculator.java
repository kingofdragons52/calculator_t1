package main.java.com.t1.calculator;

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
        setSize(400, 500);
        setLocationRelativeTo(null);
        setTitle("Calculator (Shunting Yard)");

        text = new JTextField("0");
        text.setFont(new Font("Arial", Font.BOLD, 36));
        text.setForeground(Color.WHITE);
        text.setBackground(Color.DARK_GRAY);
        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setPreferredSize(new Dimension(400, 80));

        text.addActionListener(e -> executeCalculation());
        this.add(text, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 4, 2, 2));
        panel.setBackground(Color.BLACK);

        digitButtons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            final int digit = i;
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].setBackground(Color.LIGHT_GRAY);
            digitButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            digitButtons[i].addActionListener(e -> {
                String current = text.getText();
                if (current.equals("0")) {
                    text.setText(String.valueOf(digit));
                } else {
                    text.setText(current + digit);
                }
            });
        }

        String[] simpleOps = {"+", "-", "×", "/", "(", ")", "."};
        for (String op : simpleOps) {
            JButton btn = new JButton(op);
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(e -> {
                String current = text.getText();
                if (current.equals("0")) {
                    text.setText(op.equals("(") ? op : current + op);
                } else {
                    text.setText(current + op);
                }
            });
            opButtons.put(op, btn);
        }

        String[] advancedOps = {"√", "x²", "1/x", "%"};
        for (String op : advancedOps) {
            JButton btn = new JButton(op);
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(e -> {
                String current = text.getText();
                if (current.equals("0")) {
                    text.setText(op + "(");
                } else {
                    text.setText(current + op + "(");
                }
            });
            opButtons.put(op, btn);
        }

        JButton clearBtn = new JButton("C");
        clearBtn.setBackground(Color.LIGHT_GRAY);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 18));
        clearBtn.setForeground(Color.BLACK);
        clearBtn.addActionListener(e -> text.setText("0"));

        JButton backspaceBtn = new JButton("⌫");
        backspaceBtn.setBackground(Color.LIGHT_GRAY);
        backspaceBtn.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        backspaceBtn.addActionListener(e -> {
            String current = text.getText();
            if (current.length() > 0 && !current.equals("0")) {
                String next = current.substring(0, current.length() - 1);
                text.setText(next.isEmpty() ? "0" : next);
            }
        });

        JButton equalsBtn = new JButton("=");
        equalsBtn.setBackground(Color.LIGHT_GRAY);
        equalsBtn.setFont(new Font("Arial", Font.BOLD, 18));
        equalsBtn.addActionListener(e -> executeCalculation());

        panel.add(opButtons.get("%"));   panel.add(opButtons.get("("));   panel.add(opButtons.get(")"));   panel.add(clearBtn);
        panel.add(opButtons.get("1/x")); panel.add(opButtons.get("x²")); panel.add(opButtons.get("√"));   panel.add(backspaceBtn);
        panel.add(digitButtons[1]);      panel.add(digitButtons[2]);      panel.add(digitButtons[3]);      panel.add(opButtons.get("+"));
        panel.add(digitButtons[4]);      panel.add(digitButtons[5]);      panel.add(digitButtons[6]);      panel.add(opButtons.get("-"));
        panel.add(digitButtons[7]);      panel.add(digitButtons[8]);      panel.add(digitButtons[9]);      panel.add(opButtons.get("×"));
        panel.add(opButtons.get("."));   panel.add(digitButtons[0]);      panel.add(equalsBtn);            panel.add(opButtons.get("/"));

        this.add(panel, BorderLayout.CENTER);

        this.setFocusable(true);
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char ch = e.getKeyChar();
                String current = text.getText();
                if (Character.isDigit(ch)) {
                    if (current.equals("0")) text.setText(String.valueOf(ch));
                    else text.setText(current + ch);
                } else if (ch == '+' || ch == '-' || ch == '/' || ch == '(' || ch == ')' || ch == '.') {
                    text.setText(current.equals("0") && ch == '(' ? "(" : current + ch);
                } else if (ch == '*') {
                    text.setText(current + "×");
                } else if (ch == '\n' || ch == '=') {
                    executeCalculation();
                } else if (ch == '\b') {
                    if (current.length() > 0 && !current.equals("0")) {
                        String next = current.substring(0, current.length() - 1);
                        text.setText(next.isEmpty() ? "0" : next);
                    }
                }
            }
        });
    }

    private void executeCalculation() {
        try {
            String expression = text.getText().replace("×", "*");
            double result = ExpressionParser.evaluate(expression);

            if (result == (long) result) {
                text.setText(String.valueOf((long) result));
            } else {
                text.setText(String.valueOf(result));
            }
        } catch (RuntimeException ex) {
            text.setText(ex.getMessage());
        } catch (Exception ex) {
            text.setText("Ошибка");
        }
    }
}