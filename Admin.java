package it.centroestetico;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Admin extends JFrame{
	
	private JLabel visualizzaLabel, campo1, campo2;
	private JButton visualizzaAppuntamentiButton, visualizzaFatturaButton, backButton;
	private JTextArea visualizzaArea;
	private Connection conn;
	private Statement stmt;
	private int i=0;
	
	public Admin() {
		
		visualizzaLabel = new JLabel("Benvenuto Admin");
		visualizzaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		visualizzaLabel.setVerticalAlignment(SwingConstants.CENTER);
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		visualizzaLabel.setFont(buttonFont);
		visualizzaLabel.setBackground(Color.decode("#FFE4C4"));
		visualizzaLabel.setForeground(Color.decode("#A52A2A"));
		
		visualizzaArea = new JTextArea();
		
		visualizzaAppuntamentiButton = new JButton("Appuntamenti");
		Font buttonFont1 = new Font("Arial", Font.BOLD, 35);
		visualizzaAppuntamentiButton.setFont(buttonFont1);
		visualizzaAppuntamentiButton.setBackground(Color.decode("#FFE4C4"));
		visualizzaAppuntamentiButton.setForeground(Color.decode("#A52A2A"));
		
		
		visualizzaFatturaButton = new JButton("Fattura");
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		visualizzaFatturaButton.setFont(buttonFont2);
		visualizzaFatturaButton.setBackground(Color.decode("#FFE4C4"));
		visualizzaFatturaButton.setForeground(Color.decode("#A52A2A"));
		
		backButton = new JButton("Torna Indietro");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		backButton.setFont(buttonFont3);
		backButton.setBackground(Color.decode("#FFE4C4"));
		backButton.setForeground(Color.decode("#A52A2A"));
		campo1 = new JLabel("");
		campo2 = new JLabel("");
		
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(campo1);
		panel.add(visualizzaLabel, BorderLayout.NORTH);
		panel.add(visualizzaArea, BorderLayout.CENTER);
		panel.add(visualizzaAppuntamentiButton, BorderLayout.EAST);
		panel.add(visualizzaFatturaButton, BorderLayout.WEST);
		panel.add(backButton, BorderLayout.SOUTH);
		
		
		
		add(panel);
		
		
		
		setTitle("Admin Console");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Accesso();
			}
		}
				);
		
		visualizzaAppuntamentiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i++;
				
				if(i%2!=0) {
					visualizzaArea.setVisible(true);
					
					
					 try {
				        	
				        	Properties props = new Properties();
				            FileInputStream in = new FileInputStream("database.propreties");
				                props.load(in);
				                in.close();
				                
				            String url = props.getProperty("url");
				            String username = props.getProperty("username");
				            String password = props.getProperty("password");
				            conn = DriverManager.getConnection(url, username, password);
				            
				            String sql1 = "SELECT * FROM Appuntamenti WHERE eseguito=0 ";
				            
				            stmt = conn.createStatement();
				            
				            ResultSet rs = stmt.executeQuery(sql1);
				            
				            visualizzaArea.setText("");
				            while (rs.next()) {
				            	String id_staff = rs.getString("id_staff");
				            	String nome = rs.getString("nome");
				            	String cognome = rs.getString("cognome");
				            	String numero = rs.getString("numero");
				            	String data = rs.getString("data_appuntamento");
				            	visualizzaArea.append("Staff Numero: " + id_staff +  "\n" + nome + " " + cognome + "\n" + "+39 " + numero + "\n" + "Data: " + data );
				            	
				            } 
				            
				            rs.close();
				            stmt.close();
				            conn.close();
				         
				            					           				
				}catch(Exception ex) {
					ex.printStackTrace();
				}					
			} else if(i%2==0) {
				visualizzaArea.setText("");
				visualizzaArea.setVisible(false);
			}
				
		}
	 }
				);
		
		visualizzaFatturaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new Fattura();
				
		}
	 }
				);
		
		
		
		
		
		
		
	}

}
