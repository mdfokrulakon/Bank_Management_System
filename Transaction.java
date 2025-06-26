package bankmanagementsystem;

import java.awt.*;

import java.awt.event.*;


import java.io.*;

import java.util.*;

import javax.swing.*;

public class Transaction extends JFrame implements ActionListener {

	JLabel l1, l2;
	JButton b1, b2, b4, b5, b6, b7;

	

	public String cardno;

	Transaction(String cardno) {

		this.cardno = cardno;
		new Pin(this.cardno);
                new Withdrawl(this.cardno);
                new Deposit(this.cardno);
                new MiniStatement(this.cardno);
                new BalanceEnquiry1(this.cardno);
	}

	Transaction() {

		

		setLayout(null);

		ImageIcon i1 = new ImageIcon("/Users/imamhossain/Downloads/atm.png");
		Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		l2 = new JLabel(i3);
		l2.setBounds(0, 0, 960, 1080);
		add(l2);

		l1 = new JLabel("Please Select Your Transaction");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 16));
		l1.setBounds(235, 400, 700, 35);
		l2.add(l1);

		b1 = new JButton("DEPOSIT");
		b1.setBounds(170, 498, 150, 35);
		b1.addActionListener(this);
		l2.add(b1);

		b2 = new JButton("CASH WITHDRAWL");
		b2.setBounds(390, 498, 150, 35);
		b2.addActionListener(this);
		l2.add(b2);

		/*b4 = new JButton("MINI STATEMENT");
		b4.setBounds(170, 542, 150, 35);
		b4.addActionListener(this);
		l2.add(b4);*/

		b5 = new JButton("PIN CHANGE");
		b5.setBounds(390, 542, 150, 35);
		b5.addActionListener(this);
		l2.add(b5);

		b6 = new JButton("BALANCE ENQUIRY");
		b6.setBounds(170, 587, 150, 35);
		b6.addActionListener(this);
		l2.add(b6);

		b7 = new JButton("EXIT");
		b7.setBounds(390, 587, 150, 35);
		b7.addActionListener(this);
		l2.add(b7);

		setSize(960, 1080);
		setLocation(500, 0);
		
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b1) {
			setVisible(false);
			new Deposit().setVisible(true);
		} else if (ae.getSource() == b2) {
			setVisible(false);
			new Withdrawl().setVisible(true);
		 
		} else if (ae.getSource() == b5) {
			setVisible(false);
			new Pin().setVisible(true);
		} else if (ae.getSource() == b6) {
			this.setVisible(false);
			new BalanceEnquiry1().setVisible(true);
		} else if (ae.getSource() == b7) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {

		new Transaction();

	}
}
