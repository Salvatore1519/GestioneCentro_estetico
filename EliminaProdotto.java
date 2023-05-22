package it.centroestetico;
 
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.util.*;


public class EliminaProdotto extends JFrame{

ArrayList<Double> ordini = new ArrayList<Double>();
	
	private Connection conn;
	private JLabel idOrdineLabel;
	private JTextField idField;
	private JButton confermaButton;
	private JButton indietro;
	
	public EliminaProdotto() {
		
		idOrdineLabel = new JLabel("Inserisci ID Prodotto:");
		idOrdineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idOrdineLabel.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont = idOrdineLabel.getFont();
		int labelFontSize = labelFont.getSize() + 25;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		idOrdineLabel.setFont(newFont);
		idOrdineLabel.setForeground(Color.WHITE);
		
		idField = new JTextField();
		Font font = new Font("Arial", Font.PLAIN, 50);
		idField.setFont(font);
		
		confermaButton = new JButton("Conferma");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		confermaButton.setFont(buttonFont);
		confermaButton.setBackground(Color.WHITE);
		confermaButton.setForeground(Color.PINK);
		
		indietro = new JButton("Torna In Gestione Prodotti");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont3);
		indietro.setBackground(Color.WHITE);
		indietro.setForeground(Color.PINK);
		
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
    	JPanel panel = new JPanel(new GridLayout(6,2)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("sfondo.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
    	
		
		
		
		panel.setLayout(new BorderLayout());
		
		panel.add(idOrdineLabel, BorderLayout.NORTH);
		panel.add(idField, BorderLayout.CENTER);
		panel.add(confermaButton, BorderLayout.SOUTH);
		panel.add(indietro, BorderLayout.WEST);
		add(panel);
		
		
		setTitle("Elimina Ordine");
		setSize(1180,820);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		panel = new JPanel(new GridLayout(6,2)) {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon imageIcon = new ImageIcon("spa.jpg");
	            Image image = imageIcon.getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	        }
	    };
		
		
		confermaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				PreparedStatement stmt = null;
				String sql = "DELETE FROM prodotti WHERE id = ?";
				
				try {
					String id_modifica = idField.getText().toString();
					
					Properties props = new Properties();
					FileInputStream in;
					try {
						in = new FileInputStream("database.propreties");
						props.load(in);
						in.close();
					
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
						
					
					String url = props.getProperty("url");
					String username = props.getProperty("username");
					String password = props.getProperty("password");
					
					
					
					
				
					
				
					conn = DriverManager.getConnection(url, username, password);
					
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, id_modifica);
					stmt.executeUpdate();
					
					idField.setText(" ");
					
					JOptionPane.showMessageDialog(null, "Prodotto cancellato correttamente");
					
					
				} catch(SQLException | IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Prodotto non cancellato correttamente");
				}
				
			}
		}
				);
		
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestioneProdotti();
				dispose();
			}
		}
				);
		
	}
	
}