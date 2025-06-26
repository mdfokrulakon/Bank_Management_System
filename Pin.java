package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Pin extends JFrame implements ActionListener {
    private JPasswordField newPasswordField, reEnterPasswordField;
    private JButton changeButton, backButton;
    private JLabel titleLabel, newPinLabel, reEnterPinLabel, imageLabel;
    private static String cardNumber;

    Pin(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    Pin() {
        setLayout(null);

        ImageIcon atmIcon = new ImageIcon("/Users/imamhossain/Downloads/atm.png");
        Image scaledImage = atmIcon.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, 960, 1080);
        add(imageLabel);

        titleLabel = new JLabel("CHANGE YOUR PIN");
        titleLabel.setFont(new Font("System", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(280, 330, 800, 35);
        imageLabel.add(titleLabel);

        newPinLabel = new JLabel("New PIN:");
        newPinLabel.setFont(new Font("System", Font.BOLD, 16));
        newPinLabel.setBounds(180, 385, 200, 35);
        newPinLabel.setForeground(Color.WHITE);
        imageLabel.add(newPinLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Raleway", Font.BOLD, 25));
        newPasswordField.setBounds(350, 390, 180, 25);
        imageLabel.add(newPasswordField);

        reEnterPinLabel = new JLabel("Re-Enter New PIN:");
        reEnterPinLabel.setFont(new Font("System", Font.BOLD, 16));
        reEnterPinLabel.setForeground(Color.WHITE);
        reEnterPinLabel.setBounds(180, 440, 200, 35);
        imageLabel.add(reEnterPinLabel);

        reEnterPasswordField = new JPasswordField();
        reEnterPasswordField.setFont(new Font("Raleway", Font.BOLD, 25));
        reEnterPasswordField.setBounds(350, 440, 180, 25);
        imageLabel.add(reEnterPasswordField);

        changeButton = new JButton("CHANGE");
        changeButton.setBounds(390, 588, 150, 35);
        changeButton.addActionListener(this);
        imageLabel.add(changeButton);

        backButton = new JButton("BACK");
        backButton.setBounds(390, 633, 150, 35);
        backButton.addActionListener(this);
        imageLabel.add(backButton);

        setSize(960, 1080);
        setLocation(500, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String newPassword = newPasswordField.getText();
            String reEnterPassword = reEnterPasswordField.getText();

            if (!newPassword.equals(reEnterPassword)) {
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }

            if (ae.getSource() == changeButton) {
                if (newPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                } else if (reEnterPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                } else {
                    File file = new File("/Users/imamhossain/Downloads/imam/Bank.txt");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    PrintWriter writer = new PrintWriter(new FileWriter(file, true));
                    ArrayList<String> lines = new ArrayList<>();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(" ");
                        if (parts[1].equals(cardNumber)) {
                            parts[2] = newPassword;
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

                    JOptionPane.showMessageDialog(null, "PIN changed successfully");
                    setVisible(false);
                    new Transaction().setVisible(true);
                }
            } else if (ae.getSource() == backButton) {
                new Transaction().setVisible(true);
                setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin();
    }
}



