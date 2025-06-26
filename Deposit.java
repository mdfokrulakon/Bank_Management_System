package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Deposit extends JFrame implements ActionListener {
    private JButton depositButton, d500, d1000, d2000, d4000, d5000, d10000, backButton, clearButton;
    private JTextField amountField;
    private JLabel imageLabel, textLabel;
    private int totalAmount;
    private static String cardNumber;

    Deposit(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    Deposit() {
        setLayout(null);

        ImageIcon atmIcon = new ImageIcon("/Users/imamhossain/Downloads/atm.png");
        Image scaledImage = atmIcon.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, 960, 1080);
        add(imageLabel);

        textLabel = new JLabel("Enter the amount you want to deposit");
        textLabel.setFont(new Font("System", Font.BOLD, 16));
        textLabel.setBounds(170, 350, 400, 20);
        imageLabel.add(textLabel);

        amountField = new JTextField();
        amountField.setFont(new Font("Raleway", Font.BOLD, 22));
        amountField.setBounds(170, 400, 150, 30);
        imageLabel.add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(150, 635, 150, 30);
        depositButton.setFont(new Font("Raleway", Font.BOLD, 16));
        depositButton.addActionListener(this);
        imageLabel.add(depositButton);

        d500 = new JButton("500");
        d500.setBounds(150, 502, 150, 30);
        d500.setFont(new Font("Raleway", Font.BOLD, 16));
        d500.addActionListener(this);
        imageLabel.add(d500);

        // Add other buttons and components...

        backButton = new JButton("Back");
        backButton.setBounds(400, 635, 150, 30);
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
            if (ae.getSource() == depositButton) {
                String amt = amountField.getText();
                Date date = new Date();
                if (amt.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter the amount you want to deposit");
                } else {
                    int amt1 = Integer.valueOf(amt);

                    File file = new File("/Users/imamhossain/Downloads/imam/Bank.txt");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    PrintWriter writer = new PrintWriter(new FileWriter(file, true));
                    ArrayList<String> lines = new ArrayList<>();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(" ");
                        if (parts[1].equals(cardNumber)) {
                            int amt2 = Integer.valueOf(parts[3]);
                            totalAmount = amt1 + amt2;
                            parts[3] = "" + totalAmount;
                            line = parts[0] + " " + parts[1] + " " + parts[2] + " " + parts[3];
                            lines.add(line);
                        } else {
                            lines.add(line);
                        }
                    }

                    PrintWriter writer2 = new PrintWriter(file);
                    for (String x : lines) {
                        writer2.println(x);
                        writer2.flush();
                    }

                    JOptionPane.showMessageDialog(null,
                            "Tk: " + amt + " Successfully deposited." + "\nDate:" + date);
                    setVisible(false);
                    MiniStatement.addTransaction("Deposit of " + amt1 + " Total:" + totalAmount);
                    new MiniStatement(amt1, totalAmount).setVisible(true);
                    new Transaction().setVisible(true);
                }

            } else if (ae.getSource() == d500) {
                amountField.setText("500");
            } else if (ae.getSource() == d1000) {
                amountField.setText("1000");
            } else if (ae.getSource() == clearButton) {
                amountField.setText("");
            } else if (ae.getSource() == backButton) {
                setVisible(false);
                new Transaction().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit();
    }
}

