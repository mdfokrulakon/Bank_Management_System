package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class BalanceEnquiry1 extends JFrame implements ActionListener {
    private JButton backButton;
    private int accountBalance;
    private static String cardNumber;

    BalanceEnquiry1(String cardNumber) {
        this.cardNumber = cardNumber;
        fetchAccountBalance();
        initializeUI();
    }

    BalanceEnquiry1() {
        fetchAccountBalance();
        initializeUI();
    }

    private void fetchAccountBalance() {
        try {
            File file = new File("/Users/imamhossain/Downloads/imam/Bank.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[1].equals(cardNumber)) {
                    accountBalance = Integer.parseInt(parts[3]);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeUI() {
        setLayout(null);

        ImageIcon atmIcon = new ImageIcon("/Users/imamhossain/Downloads/atm.png");
        Image scaledImage = atmIcon.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, 900, 900);
        add(imageLabel);

        backButton = new JButton("Back");
        backButton.setBounds(355, 520, 150, 30);
        backButton.addActionListener(this);
        imageLabel.add(backButton);

        JLabel balanceLabel = new JLabel("Your Current Account Balance is TK " + accountBalance);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(170, 300, 400, 30);
        imageLabel.add(balanceLabel);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transaction().setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry1();
    }
}

