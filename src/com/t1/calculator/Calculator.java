package com.t1.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class Calculator extends JFrame implements ActionListener{

    private JButton[] digitbuttons;
    private JButton[] operators;
    private String first = "", operator = "", second = "";
    private JTextField text;
    private JButton bPlus, bMinus, bMultiply, bDivide, bReciprocal, bSquare, bSqrt, bComma, bEquals, bBackspace;


    public Calculator(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 1, 1));
        panel.setBackground(Color.black);

        digitbuttons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            digitbuttons[i] = new JButton(String.valueOf(i));
            digitbuttons[i].addActionListener(this);
            digitbuttons[i].setBackground(Color.LIGHT_GRAY);
        }

        bPlus = new JButton("+");
        bMinus = new JButton("-");
        bMultiply = new JButton("*");
        bDivide = new JButton("/");
        bReciprocal = new JButton("1/x");
        bSquare = new JButton("x²");
        bSqrt = new JButton("√");
        bComma = new JButton(",");
        bEquals = new JButton("=");
        bBackspace = new JButton("BckSpc");

        JButton[] operatorButtons = {bPlus, bMinus, bMultiply, bDivide,  bReciprocal, bSquare, bSqrt, bComma, bEquals, bBackspace};
        for (JButton button : operatorButtons) {
            button.addActionListener(this);
            button.setBackground(Color.LIGHT_GRAY);
        }

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

        panel.add(bComma);
        panel.add(digitbuttons[0]);
        panel.add(bEquals);
        panel.add(bDivide);



        text = new JTextField("Поле для ввода");
        Font font = new Font("Arial", Font.BOLD, 18);
        text.setForeground(Color.WHITE);
        text.setFont(font);
        text.setPreferredSize(new Dimension(300, 70));
        text.setBackground(Color.DARK_GRAY);
        this.add(text, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttontext = e.getActionCommand();
        System.out.println(buttontext + "кнопка нажата");
    }
}
