package it.centroestetico;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.*;
import java.util.*;

public class ModificaTrattamento extends JFrame{

	private JPanel panel;
	private JButton aggiornaButton,indietroButton;
	private JLabel idLabel,prezzoLabel,nomeLabel;
	private JTextField idField,nomeField,prezzoField;
	
	public ModificaTrattamento() {
		panel = new JPanel(new GridLayout(4,1));
		setTitle("Aggiorna Prodotti");
		setSize(1920,1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		idLabel = new JLabel("Inserisci L'Id Del prodotto Da modificare");
		nomeLabel = new JLabel("Inserisci Il Nuovo Nome Del Prodotto");
		prezzoLabel = new JLabel("Inserisci Il Nuovo Prezzo");
		
		
		idField = new JTextField();
		
		nomeField= new JTextField();
		
		prezzoField = new JTextField();
		
		aggiornaButton= new JButton("Aggiorna Prodotto");
		aggiornaButton.setBackground(Color.MAGENTA);
		aggiornaButton.setForeground(Color.WHITE);
		indietroButton= new JButton("Torna Indietro");
		indietroButton.setBackground(Color.MAGENTA);
		indietroButton.setForeground(Color.WHITE);
		
		
		panel.add(idLabel);
		panel.add(idField);
		panel.add(nomeLabel);
	    panel.add(nomeField);
	    panel.add(prezzoLabel);
	    panel.add(prezzoField);
		panel.add(aggiornaButton);
		panel.add(indietroButton);
		
		indietroButton.addActionListener(e->{
			new Trattamenti();
		});
		aggiornaButton.addActionListener(aggiorna->{
			
			String nomeAggiornato = nomeField.getText();
			
			
			double prezzo = (double) Double.parseDouble(prezzoField.getText());
			 
			
	
			Properties props = new Properties();
			FileInputStream in;
			try {
				in = new FileInputStream("database.propreties");

				props.load(in);
				in.close();
			
				
			} catch (FileNotFoundException e1 ) {
			
				e1.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
			    int id = (int)Integer.parseInt(idField.getText());
			    conn = DriverManager.getConnection(url,username,password);
			    String sql = "UPDATE Trattamenti SET nome_trattamento =?, prezzo =? WHERE id=?";
			    stmt = conn.prepareStatement(sql);
			    stmt.setString(1,nomeAggiornato);
			    stmt.setDouble(2, prezzo);
			    stmt.setInt(3,id);
			    int rowsUpdated = stmt.executeUpdate();
			    if (rowsUpdated > 0) {
			       JOptionPane.showMessageDialog(null,"Il prodotto è stato aggiornato con successo!");
			    	System.out.println("Il prodotto è stato aggiornato con successo!");
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		
				
		
			
			
			
		});
		
		add(panel);
		setVisible(true);
	}
	
	
	
}