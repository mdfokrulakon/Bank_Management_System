package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Withdrawal extends JFrame implements ActionListener {
    private JButton withdrawButton, d500, d1000, d2000, d4000, d5000, d10000, backButton, clearButton;
    private JTextField amountField;
    private JLabel imageLabel, textLabel;
    private static String cardNumber;

    Withdrawal(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    Withdrawal() {
        setLayout(null);

        ImageIcon atmIcon = new ImageIcon("/Users/imamhossain/Downloads/atm.png");
        Image scaledImage = atmIcon.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, 960, 1080);
        add(imageLabel);

        textLabel = new JLabel("Enter the amount you want to withdraw");
        textLabel.setFont(new Font("System", Font.BOLD, 16));
        textLabel.setBounds(170, 350, 400, 20);
        textLabel.setForeground(Color.WHITE);
        textLabel.setBackground(Color.BLACK);
        imageLabel.add(textLabel);

        amountField = new JTextField();
        amountField.setFont(new Font("Raleway", Font.BOLD, 22));
        amountField.setBounds(170, 400, 150, 30);
        imageLabel.add(amountField);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(150, 635, 150, 30);
        withdrawButton.setFont(new Font("Raleway", Font.BOLD, 16));
        withdrawButton.addActionListener(this);
        imageLabel.add(withdrawButton);

        d500 = new JButton("500");
        d500.setBounds(150, 502, 150, 30);
        d500.setFont(new Font("Raleway", Font.BOLD, 16));
        d500.addActionListener(this);
        imageLabel.add(d500);

        // Add other buttons and components...

        backButton = new JButton("Back");
        backButton.setBounds(400, 635, 150, 30);
        backButton.setFont(new Font("Raleway", Font.BOLD, 16));
        backButton.addActionListener(this);
        imageLabel.add(backButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(350, 400, 130, 25);
        clearButton.setFont(new Font("Raleway", Font.BOLD, 16));
        clearButton.addActionListener(this);
        imageLabel.add(clearButton);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            // Handle actions...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Withdrawal();
    }
}
