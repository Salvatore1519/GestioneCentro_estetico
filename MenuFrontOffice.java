package it.centroestetico;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MenuFrontOffice extends JFrame{
	
	
	
	private JLabel  appuntamentiLbl, prodottoLbl, trattamentiLbl,campo1, campo2,campo3;
	private JButton appuntamentiButton, prodottoButton, trattamentiButton;
	
	public MenuFrontOffice() {
		
		appuntamentiLbl = new JLabel("APPUNTAMENTI");
		appuntamentiLbl.setHorizontalAlignment(SwingConstants.CENTER);
		appuntamentiLbl.setVerticalAlignment(SwingConstants.CENTER);
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		appuntamentiLbl.setFont(buttonFont2);
		
		prodottoLbl = new JLabel("PRODOTTI");
		prodottoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		prodottoLbl.setVerticalAlignment(SwingConstants.CENTER);
		Font buttonFont1 = new Font("Arial", Font.BOLD, 35);
		prodottoLbl.setFont(buttonFont2);	
		trattamentiLbl = new JLabel("TRATTAMENTI");
		trattamentiLbl.setHorizontalAlignment(SwingConstants.CENTER);
		trattamentiLbl.setVerticalAlignment(SwingConstants.CENTER);
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		trattamentiLbl.setFont(buttonFont3);
		
		campo1 = new JLabel("");
		campo2 = new JLabel("");
		campo3 = new JLabel("");
		
		appuntamentiButton = new JButton("Calendario");
		Font buttonFont4 = new Font("Arial", Font.BOLD, 35);
		appuntamentiButton.setFont(buttonFont4);
		appuntamentiButton.setForeground(Color.decode("#A52A2A"));
		
		
		prodottoButton = new JButton("Vetrina");
		Font buttonFont5 = new Font("Arial", Font.BOLD, 35);
		prodottoButton.setFont(buttonFont5);
		prodottoButton.setForeground(Color.decode("#A52A2A"));
		prodottoButton.setBackground(Color.decode("#FFE4C4"));
		
		trattamentiButton = new JButton("Trattamenti");
		Font buttonFont6 = new Font("Arial", Font.BOLD, 35);
		trattamentiButton.setFont(buttonFont6);
		trattamentiButton.setForeground(Color.decode("#A52A2A"));
		
		JPanel panel = new JPanel(new GridLayout(6,2));
		
		setTitle("Menu Front Office");
		setSize(1920,1080);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		panel.add(appuntamentiLbl);
		panel.add(appuntamentiButton);
		panel.add(prodottoLbl);
		panel.add(prodottoButton);
		panel.add(trattamentiLbl);
		panel.add(trattamentiButton);
		
		
		appuntamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				 SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			                CalendarioGUI gui = new CalendarioGUI();
			                gui.setVisible(true);
			            }
			        });
			}
		}
				);
		
		prodottoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GestioneProdotti();
			}
		}
				);
		
		trattamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Trattamenti();
			}
		}
				);
		
		setVisible(true);
		add(panel);
	}
	
	


}

