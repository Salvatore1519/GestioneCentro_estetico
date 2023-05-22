package it.centroestetico;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class Trattamenti extends JFrame {

	private static final String DB_URL = "jdbc:mysql://localhost/centro_estetico";
	private static final String DB_USER ="root";
	private static final String DB_PASSWORD = "12995";


	private JButton visualizzaButton;
	private JButton aggiungiButton;
	private JButton rimuoviButton;
	private JButton modificaButton;



	public Trattamenti() {

		//INIZIALIZZAZIONE DEI COMPONENTI

		visualizzaButton = new JButton("Visualizza Trattamento");
		Font buttonFont1 = new Font("Arial", Font.BOLD, 35);
		visualizzaButton.setFont(buttonFont1);
		visualizzaButton.setBackground(Color.WHITE);
		visualizzaButton.setForeground(Color.MAGENTA);

		aggiungiButton = new JButton("Aggiungi Trattamento");
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		aggiungiButton.setFont(buttonFont2);
		aggiungiButton.setBackground(Color.WHITE);
		aggiungiButton.setForeground(Color.MAGENTA);

		rimuoviButton = new JButton("Rimuovi Trattamento");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		rimuoviButton.setFont(buttonFont3);
		rimuoviButton.setBackground(Color.WHITE);
		rimuoviButton.setForeground(Color.MAGENTA);

		modificaButton = new JButton("Modifica Trattamento");
		Font buttonFont4 = new Font("Arial", Font.BOLD, 35);
		modificaButton.setFont(buttonFont3);
		modificaButton.setBackground(Color.WHITE);
		modificaButton.setForeground(Color.MAGENTA);

		JPanel panel = new JPanel(new GridLayout(4,2));
		
		panel.add(visualizzaButton);
		panel.add(aggiungiButton);
		panel.add(rimuoviButton);
		panel.add(modificaButton);
		add(panel);
		
		// Imposta le dimensioni e rendi il frame visibile
		setTitle("Trattamenti");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1920, 1080);
		setVisible(true);

		// Aggiungi gli ActionListener per i pulsanti
		visualizzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Logica per visualizzare i dati
				new VisualizzaTrattamento();
			}
		});

		aggiungiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Logica per aggiungere un trattamento
				try {
					new AggiungiTrattamento();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});

		rimuoviButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Logica per rimuovere un trattamento
				new RimuoviTrattamento();
			}
		});

		modificaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Logica per modificare un trattamento
				new ModificaTrattamento();
			}
		});


	}

	
		
	}