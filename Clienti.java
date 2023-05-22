package it.centroestetico;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Clienti extends JFrame implements ActionListener{
	
	 private JTextField searchField;
	    private JTextArea resultArea;
	    private JButton searchButton;
	    
	   private Connection conn;
		
		public Clienti() {
		 super("Clienti");

		        // Crea i componenti dell'interfaccia grafica
		        searchField = new JTextField(20);
		        resultArea = new JTextArea(20, 40);
		        
		        searchButton = new JButton("Cerca");
		        Font buttonFont3 = new Font("Arial", Font.BOLD, 15);
		        searchButton.setFont(buttonFont3);
		        searchButton.setForeground(Color.decode("#A52A2A"));

		        // Aggiungi i componenti al frame
		        JPanel panel = new JPanel();
		        panel.add(new JLabel("Cerca: "));
		        panel.add(searchField);
		        panel.add(searchButton);
		        JScrollPane scrollPane = new JScrollPane(resultArea);
		        add(panel, BorderLayout.NORTH);
		        add(scrollPane, BorderLayout.CENTER);

		        // Aggiungi il listener al bottone di ricerca
		        searchButton.addActionListener(this);

		        // Imposta le proprietà del frame
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        pack();
		        setLocationRelativeTo(null);
		        setVisible(true);
		    }


		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Connessione al database MySQL
		    	
		    	
		        try {
		        	
		        	Properties props = new Properties();
		            FileInputStream in = new FileInputStream("database.propreties");
		                props.load(in);
		                in.close();
		                
		            String url = props.getProperty("url");
		            String username = props.getProperty("username");
		            String password = props.getProperty("password");
		            conn = DriverManager.getConnection(url, username, password);
		            
		            
		            // Query di ricerca dei titoli dei libri
		            String searchQuery = "SELECT * FROM Clienti WHERE nome LIKE ?";
		            String searchTerm = "%" + searchField.getText() + "%";
		            PreparedStatement stmt = conn.prepareStatement(searchQuery);
		            stmt.setString(1, searchTerm);
		            ResultSet rs = stmt.executeQuery();
		            
		          
		            

		            // Mostra i risultati nella JTextArea
		            resultArea.setText("");
		           while(rs.next()) {       	 
		                String nome = rs.getString("nome"); 
		                String cognome = rs.getString("cognome");
		                String numero = rs.getString("numero");
		                String trattamenti = rs.getString("trattamenti_effettuati");
		                resultArea.append(nome + " " + cognome + "\n" + "+39 " + numero + "\n" + "Trattamenti: " + trattamenti);
		            } 
		                
		            
		            
		        } catch (SQLException | IOException ex) {
		           ex.printStackTrace();
		       
		        }
			
			
			
		}

			
		
}
