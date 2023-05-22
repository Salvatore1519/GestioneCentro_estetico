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
import java.util.Scanner;
import java.io.*;
import javax.swing.*;





public class GestioneProdotti extends JFrame {

	  int i=0;
	private JPanel panel,panelOrdine;
	
	private JTextField idField  ;
	private JTextArea informazioniArea;
	private JLabel nomeProdottoLabel;
	private JButton aggiornaButton,rimuoviButton,visualizzaButton,aggiungiButton;
	private JButton indietro;
	private JButton bibite;
	
	public GestioneProdotti() {
		
  // Costrutore Tavoli
		super("MenuBarDemo");
		
		
		
		

		idField = new JTextField();
		informazioniArea = new JTextArea();
		visualizzaButton = new JButton("Visualizza Ordini");
		
		JLabel gifLabel = new JLabel();
		//ImageIcon gifIcon = new ImageIcon("C:\\Users\\Salvatore\\Desktop\\ImmagginiRisotorante\\cameriere-0057.gif");
		
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		visualizzaButton.setFont(buttonFont);
		
		indietro = new JButton("Torna al menu Principale");
		Font buttonFont6 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont6);
		indietro.setBackground(Color.PINK);
		
		bibite = new JButton("Categoria Bibite");
		Font buttonFont7 = new Font("Arial", Font.BOLD, 35);
		bibite.setFont(buttonFont7);
		bibite.setBackground(Color.WHITE);
		bibite.setForeground(Color.PINK);
		
	
		

		//AGGIUNTA DEI COMPONENTI		
		 panelOrdine = new JPanel(new BorderLayout());
		
		panelOrdine.add(informazioniArea, BorderLayout.CENTER);
		panelOrdine.add(visualizzaButton, BorderLayout.LINE_START);
		

		//AGGIUNTA SCROLL		
		JScrollPane    scroll = new JScrollPane(informazioniArea);
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panelOrdine.add(scroll);
		
	
		setTitle("Cucina");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


		
		


		
		
		
		
		
		
		
		
		
		
		
		
		   // MenuBar
		rimuoviButton = new JButton("Rimuovi Prodotti");
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		rimuoviButton.setFont(buttonFont2);
		rimuoviButton.setBackground(Color.WHITE);
		rimuoviButton.setForeground(Color.PINK);
		
		aggiornaButton = new JButton("Aggiorna Prodotti");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		aggiornaButton.setFont(buttonFont3);
		aggiornaButton.setBackground(Color.WHITE);
		aggiornaButton.setForeground(Color.PINK);
		
		aggiungiButton = new JButton("Aggiungi Prodotti");
		Font buttonFont4 = new Font("Arial", Font.BOLD, 35);
		aggiungiButton.setFont(buttonFont4);
		aggiungiButton.setBackground(Color.WHITE);
		aggiungiButton.setForeground(Color.PINK);
		
		visualizzaButton = new JButton("Visualizza Prodotti");
		Font buttonFont5 = new Font("Arial", Font.BOLD, 35);
		visualizzaButton.setFont(buttonFont5);
		visualizzaButton.setBackground(Color.WHITE);
		visualizzaButton.setForeground(Color.PINK);
		
		
	
		
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
		setTitle("Gestione Prodotti");
		setSize(1180,820);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);



	
		
		
		// Creazione Logo 
		panel = new JPanel(new GridLayout(6,2)) {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon imageIcon = new ImageIcon("spa.jpg");
	            Image image = imageIcon.getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	        }
	    };
		
		
		
		
		panelOrdine = new JPanel();
		
		
		
		
		panel.add(aggiornaButton);
		
		panel.add(rimuoviButton);
		
		panel.add(visualizzaButton);
		
		panel.add(aggiungiButton);
		
	
		
		panel.add(indietro);

		panel.setLayout(new  GridLayout(10,2));
         gifLabel.setIcon(icona);
         panel.add(gifLabel);

		
		
		
		
		
		super.getContentPane().add(panel, BorderLayout.CENTER);




		// Bottoni Selezione Categorie
	
		aggiornaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
			new AggiornaProdotto();
			dispose();
			
			}
		});
		
		
		
		

		rimuoviButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
				new EliminaProdotto();
				dispose();
		
			
			}
		});
		
		visualizzaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
			try {
				new VisualizzaProdotti();
				dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
			}
		});
		
		aggiungiButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
			try {
				new AggiungiProdotto();
				dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			}
		});
		
		
		
		
		
		
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Accesso();
				dispose();
			}
		}
				);
		
		aggiornaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AggiornaProdotto();
				dispose();
			}
		}
				);
		
		
		add(panel);
		
		setVisible(true);
		
		
	}
	















}