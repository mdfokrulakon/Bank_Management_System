package bankmanagementsystem;

import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class MiniStatement extends JFrame {
    private static String cardNumber;
    private static int depositAmount, totalAmount;
    private static ArrayList<String> lastTenTransactions = new ArrayList<>();

    MiniStatement(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    MiniStatement(int depositAmount, int totalAmount) {
        setTitle("Mini Statement");
        setLayout(null);

        ImageIcon bankLogo = new ImageIcon("/Users/imamhossain/Downloads/East West logo.png");
        Image scaledImage = bankLogo.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setBounds(30, 10, 100, 80);
        add(logoLabel);

        JLabel bankLabel = new JLabel("East West Bank");
        bankLabel.setBounds(220, 30, 300, 20);
        bankLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(bankLabel);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String todayDate = dateFormat.format(new Date());

        JLabel dateLabel = new JLabel("Date: " + todayDate);
        dateLabel.setBounds(440, 120, 150, 20);
        add(dateLabel);

        JLabel cardLabel = new JLabel("Card NO: " + cardNumber);
        cardLabel.setBounds(20, 120, 400, 20);
        cardLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        add(cardLabel);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/imamhossain/Downloads/imam/Transaction.txt"));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < 10) {
                JLabel transactionLabel = new JLabel(line);
                transactionLabel.setFont(new Font("Arial", Font.BOLD, 15));
                transactionLabel.setBounds(150, 200 + i * 50, 300, 60);
                add(transactionLabel);
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSize(600, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void addTransaction(String transaction) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String todayDate = dateFormat.format(new Date());

        lastTenTransactions.add(todayDate + ": " + transaction);

        if (lastTenTransactions.size() > 10) {
            lastTenTransactions.remove(9);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/imamhossain/Downloads/imam/Transaction.txt"))) {
            for (String trans : lastTenTransactions) {
                writer.write(trans);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MiniStatement(depositAmount, totalAmount);
    }
}
