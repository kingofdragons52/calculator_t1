package main.java.calculator;

import javax.swing.*;
import java.awt.*;

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
        // panel.add(c);

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


        JButton[] operatorButtons = {bPlus, bMinus, bMultiply, bDivide,  bReciprocal, bSquare, bSqrt, bpoint, bEquals, bBackspace, bracketLeft, bracketRight, percent, clear, sin, cos, log, degree, e, pi };


        bPlus.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText(current + "+");
            } else {
                text.setText(text.getText() + "+");
            }
        });


        bMinus.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText(current + "-");
            } else {
                text.setText(text.getText() + "-");
            }
        });

        bMultiply.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText(current + "×");
            } else {
                text.setText(text.getText() + "×");
            }
        });

        bDivide.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText(current + "/");
            } else {
                text.setText(text.getText() + "/");
            }
        });

        bSquare.addActionListener(e ->
                text.setText(text.getText() + "²"));

        bSqrt.addActionListener(e ->
                text.setText("√(" + text.getText() + ")"));

        bReciprocal.addActionListener(e ->
                text.setText("1/(" + text.getText() + ")"));


        bpoint.addActionListener(e -> {
            String current = text.getText();
            if (current.equals("0")) {
                text.setText(current + ".");
            } else {
                text.setText(text.getText() + ".");
            }
        });

        bEquals.addActionListener(e -> {                           // =
                String expression = text.getText();
                double result = ExpressionParser.evaluate(expression);
                text.setText(String.valueOf(result));
        });

        bBackspace.addActionListener(e -> {                       // BckSpc
            String deleted = text.getText();
            if (deleted.equals("0")) {
                text.setText(deleted.substring(0, deleted.length() - 1));
            }
            if (deleted.length() > 0) {
                text.setText(deleted.substring(0, deleted.length() - 1));
            }

        });

        bracketRight.addActionListener(e ->{
            String current = text.getText();
            if (current.equals("0")) {
                text.setText("(" + current);
            } else {
                text.setText(text.getText() + "(");
            }

        });

        bracketLeft.addActionListener(e ->{
            String current = text.getText();
            if (current.equals("0")) {
                text.setText(current + ")");
            } else {
                text.setText(text.getText() + ")");
            }

        });

        clear.addActionListener(e ->{
            text.setText("0");
        });

        sin.addActionListener(e ->{

        });
        cos.addActionListener(e ->{

        });

        log.addActionListener(e ->{

        });

        degree.addActionListener(e ->{

        });

        e.addActionListener(e ->{

        });

        pi.addActionListener(e ->{

        });

        for (JButton button : operatorButtons) {
            button.setBackground(Color.LIGHT_GRAY);
        };



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
        Font font = new Font("Arial", Font.BOLD, 36);


        text.setForeground(Color.WHITE);
        text.setFont(font);
        text.setPreferredSize(new Dimension(390, 90));
        text.setBackground(Color.DARK_GRAY);
        this.add(text, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }

}
