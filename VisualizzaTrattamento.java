package it.centroestetico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;


public class VisualizzaTrattamento extends JFrame{

	ArrayList<Double> ordini = new ArrayList<Double>();

	private Connection conn;
	private JLabel welcomeLabel,idOrdine;
	private JButton visualizzaButton, statoButton, deleteButton,confermaButton,esciButton;
	private JTextArea informazioniArea;
	private JTextField idField;

	int i = 0;

	public VisualizzaTrattamento() {

		//INIZIALIZZAZIONE DEI COMPONENTI
		welcomeLabel = new JLabel("Trattamenti");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.MAGENTA);
		Font labelFont = welcomeLabel.getFont();
		int labelFontSize = labelFont.getSize() + 25;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		welcomeLabel.setFont(newFont);
		informazioniArea = new JTextArea();
		Font font = informazioniArea.getFont();
		float size = font.getSize() + 20.0f;
		Font newFont3 = font.deriveFont(size);
		informazioniArea.setFont(newFont3);
		visualizzaButton = new JButton("Visualizza Trattamenti");
		visualizzaButton.setBackground(Color.MAGENTA);
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		visualizzaButton.setFont(buttonFont);
		visualizzaButton.setForeground(Color.WHITE);
		esciButton = new JButton("Torna Ai Menu");
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		esciButton.setFont(buttonFont2);
		esciButton.setBackground(Color.decode("#FFE4C4"));
		esciButton.setForeground(Color.decode("#A52A2A"));


		//AGGIUNTA DEI COMPONENTI		
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new BorderLayout());
		ImageIcon icona = new ImageIcon("");
		setIconImage(icona.getImage());

		panel.add(welcomeLabel, BorderLayout.NORTH);
		panel.add(informazioniArea, BorderLayout.CENTER);
		panel.add(visualizzaButton, BorderLayout.LINE_START);
		panel.add(esciButton, BorderLayout.LINE_END);
		add(panel);




		//AGGIUNTA SCROLL		
		JScrollPane   scroll = new JScrollPane(informazioniArea);
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panel.add(scroll);

		ImageIcon icona1 = new ImageIcon("");
		setIconImage(icona1.getImage());
		setTitle("Visualizza Trattamenti");
		setSize(1920,1080);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// Bottone Visuallizza Ordine Utente 
		visualizzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				i++;

				if(i%2!=0) {
					informazioniArea.setVisible(true);




					try {
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/centro_estetico",
								"root", "lacoste1999!");

						Statement stmt = conn.createStatement();

						String sql = "SELECT * FROM Trattamenti ";

						ResultSet rs = stmt.executeQuery(sql);

						while (rs.next()) {
							int idTrattamento = rs.getInt("id");
							String Trattamento2 = rs.getString("nome_trattamento");		                
							double prezzo = rs.getDouble("prezzo");
							informazioniArea.append("ID Trattamento" + idTrattamento +  "\nNomeTrattamento: "+ Trattamento2  + "\nPrezzo: " + prezzo);		                
						} 



						rs.close();
						stmt.close();
						conn.close();



					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if(i%2==0) {
					informazioniArea.setText("");
					informazioniArea.setVisible(false);
				}

			}
		}
				);

		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Trattamenti();
				dispose();
			}
		}
				);

		add(panel);
		setVisible(true);
	}
	
}

