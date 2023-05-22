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

public class AggiornaProdotto extends JFrame{

	private JPanel panel;
	private JButton aggiornaButton,indietroButton,visualizzaButton;
	private JLabel idLabel,prezzoLabel,nomeLabel;
	private JTextField idField,nomeField,prezzoField;

	
	
	
	
	public AggiornaProdotto() {
		
		
		
		
		panel = new JPanel(new GridLayout(6,2)) {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon imageIcon = new ImageIcon("spa.jpg");
	            Image image = imageIcon.getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	        }
	    };
		
		
		
		
		
		
		idLabel = new JLabel("Inserisci L'Id Del prodotto Da modificare");
		nomeLabel = new JLabel("Inserisci Il Nuovo Nome Del Prodotto");
		prezzoLabel = new JLabel("Inserisci Il Nuovo Prezzo");
		
		
		idField = new JTextField();
		
		nomeField= new JTextField();
		
		prezzoField = new JTextField();
		
		visualizzaButton = new JButton("**Visualizza**");
		
		aggiornaButton= new JButton("Aggiorna Prodotto");
		indietroButton= new JButton("Torna Indietro");
		
		aggiornaButton.setBackground(Color.decode("#A52A2A"));
		indietroButton.setBackground(Color.decode("#A52A2A"));
		visualizzaButton.setBackground(Color.decode("#A52A2A"));
		aggiornaButton.setBackground(Color.decode("#A52A2A"));
		
		
		visualizzaButton.setBackground(Color.decode("#A52A2A"));
		panel.setBackground(Color.decode("#A52A2A"));
		panel.add(idLabel);
		panel.add(idField);
		panel.add(nomeLabel);
	    panel.add(nomeField);
	    panel.add(prezzoLabel);
	    panel.add(prezzoField);
		panel.add(aggiornaButton);
		panel.add(indietroButton);
		panel.add(visualizzaButton);
		
		visualizzaButton.addActionListener(ex->{
		
			
			Properties props = new Properties();
			FileInputStream in;
			try {
				in = new FileInputStream("database.propreties");

				props.load(in);
				in.close();
			
				
		
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			
				Connection con = DriverManager.getConnection(url,username,password);
				PreparedStatement stmt =  con.prepareStatement("Select* from prodotti");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
			     int id_prodotto= rs.getInt("id");
			     String nome_prodotto = rs.getString("nome_prodotto");
			   
			     JOptionPane.showMessageDialog(null,"ID Prodotti: " + id_prodotto+ "Nome Prodotti: " + nome_prodotto);
					
				}
				
			} catch (FileNotFoundException | SQLException e1) {
				
				e1.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
			
			
			
			
		});
		
		indietroButton.addActionListener(e->{
			new GestioneProdotti();
		});
		
		// Avvia l' Aggiornamento Dei Prodotti
		
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
			    String sql = "UPDATE Prodotti SET nome_prodotto =?, prezzo_prodotto= ? WHERE id=?";
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
		setSize(1180,820);
		setTitle("Aggiorna Prodotto");
		setVisible(true);
	}

	
	
}
