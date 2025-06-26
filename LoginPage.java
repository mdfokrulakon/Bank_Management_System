package bankmanagementsystem;

import java.awt.*;

import java.awt.event.*;


import java.io.*;

import java.util.*;

import javax.swing.*;


public class Login_page extends JFrame implements ActionListener {

	JButton login, clear;
	JTextField cardTextField;
	JPasswordField pinTextField;

	String cardno = "";
	String pinno;

	Login_page() {
		setTitle("AUTOMATED TELLER MACHINE");

		setLayout(null);

		ImageIcon i1 = new ImageIcon("/Users/imamhossain/Downloads/East West logo.png");

		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel label = new JLabel(i3);
		label.setBounds(70, 10, 100, 100);
		add(label);

		JLabel title = new JLabel("East West ATM");
		title.setFont(new Font("Osward", Font.BOLD, 30));
		title.setBounds(200, 30, 400, 40);
		add(title);

		JLabel cardNo = new JLabel("Card No:");
		cardNo.setFont(new Font("Raleway", Font.BOLD, 20));
		cardNo.setBounds(200, 150, 150, 40);
		add(cardNo);

		cardTextField = new JTextField();
		cardTextField.setBounds(300, 162, 250, 20);
		cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
		add(cardTextField);

		JLabel pin = new JLabel("Pin:");
		pin.setFont(new Font("Raleway", Font.BOLD, 20));
		pin.setBounds(200, 180, 400, 40);
		add(pin);

		pinTextField = new JPasswordField();
		pinTextField.setBounds(300, 192, 250, 20);
		pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
		add(pinTextField);

		login = new JButton("Login");
		login.setBounds(350, 250, 100, 25);
		//login.setForeground(Color.WHITE);
		//login.setBackground(Color.black);
		login.addActionListener(this);
		add(login);

		clear = new JButton("clear");
		clear.setBounds(350, 280, 100, 25);
		//clear.setForeground(Color.WHITE);
		//clear.setBackground(Color.black);
		clear.addActionListener(this);
		add(clear);

		getContentPane().setBackground(Color.white);

		setSize(800, 400);
		setVisible(true);
		setLocation(350, 200);

	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == clear) {
			cardTextField.setText("");
			pinTextField.setText("");
		} else if (ae.getSource() == login) {

			try {
				File f1 = new File("/Users/imamhossain/Downloads/imam/Bank.txt");

				BufferedReader r = new BufferedReader(new FileReader(f1));

				PrintWriter w = new PrintWriter(new FileWriter(f1, true));

				ArrayList<String> arr = new ArrayList<>();

				String line;
				String line1;

				cardno = cardTextField.getText();
				new Transaction(cardno);
                                
				pinno = pinTextField.getText();

				while ((line = r.readLine()) != null) {

					String[] cut = line.split(" ");

					if (cut[1].equals(cardno) && cut[2].equals(pinno)) {
						setVisible(false);

						new Transaction().setVisible(true);

						break;
					} else
						JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");

					break;
				}
			} catch (IOException e) {
				System.out.println(e);
			}

		}

	}

	

	public static void main(String[] args) {
		new Login_page();

	}
}

