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
import java.io.IOException;
import java.awt.event.*;
import java.util.*;

public class VisualizzaProdotti extends JFrame  {
	
	
	
	private JPanel panel;
	private JButton buttonImmagini;
	private JButton indietro;
	
	
	public VisualizzaProdotti() throws IOException {
		
		
		
		super("Prodotti");
		
		
		
		
		JFrame frame = new JFrame("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
		   
		indietro = new JButton("Torna alle categorie menu");
		Font buttonFont5 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont5);
		indietro.setBackground(Color.WHITE);
		indietro.setForeground(Color.pink);
		
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
        setSize(1180, 820);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
               // ImageIcon imageIcon = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\sfondoarancione.jpg");
               // Image image = imageIcon.getImage();
             //   g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridLayout(1,2));

        
        
		    
        for (int i = 0; i < 1; i++) {
	    
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs=null;
	 
	    
	    try {
	    	Properties props = new Properties();
			FileInputStream in = new FileInputStream("database.propreties");
		
				props.load(in);
				in.close();
			
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			
			
			
	    	
	        conn = DriverManager.getConnection(url,username,password);
	        stmt=conn.createStatement();
	        rs=stmt.executeQuery("select *from prodotti");
	        
	        while (rs.next()) {
	        	
	            byte[] bytes = rs.getBytes("foto_prodotto");
	            String nomeProdotto = rs.getString("nome_prodotto");
	            int idImmagine = rs.getInt("id");
	            int prezzo_prodotto=rs.getInt("prezzo_prodotto");
	           
	            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));

	            ImageIcon icon = new ImageIcon(img.getScaledInstance(400, 400, BufferedImage.SCALE_SMOOTH));

	            JButton buttonImmagine = new JButton();
	            buttonImmagine.setIcon(icon);
	            buttonImmagine.setOpaque(false);
	            buttonImmagine.setContentAreaFilled(false);
	            buttonImmagine.setBorderPainted(false);
	            buttonImmagine.setFocusPainted(false);
	            buttonImmagine.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente
	            buttonImmagine.setVisible(true);

	            buttonImmagine.addActionListener(e->{
	                //CODICE PER AGGIUNGERE LA PORTATA ED INVIARLA ALLA CUCINA
	            	
	            	   
	            	   
	              JOptionPane.showMessageDialog(null,"Nome Prodotto: "+ nomeProdotto +"Prezzo Prodotto: " + prezzo_prodotto + "€"  );
	               
	            
	                 
	                    
	                  

	              
	            });
	            
	          
	           
	   	     
	            panel.add(buttonImmagine); // aggiungi il bottone al pannello
	            
	           
	        }
	        

	     
	        
	        rs.close();
	        stmt.close();
	        conn.close();
	    	}catch(SQLException e) {
	        e.printStackTrace();
	        }

        }
        indietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	 dispose();
        	new GestioneProdotti();
        	}
        }
        		);
        
	
		
        panel.add(indietro);
	    
	     
	    JScrollPane scrollPane = new JScrollPane(panel);
		 //scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		 getContentPane().add(scrollPane);

	     setVisible(true);
	  
	}
	
	
}
