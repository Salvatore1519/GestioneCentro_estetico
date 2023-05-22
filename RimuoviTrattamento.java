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


public class RimuoviTrattamento extends JFrame{

ArrayList<Double> ordini = new ArrayList<Double>();
	
	private Connection conn;
	private JLabel idOrdineLabel;
	private JTextField idField;
	private JButton confermaButton;
	private JButton indietro;
	
	public RimuoviTrattamento() {
		
		idOrdineLabel = new JLabel("Inserisci ID Trattamento:");
		idOrdineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idOrdineLabel.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont = idOrdineLabel.getFont();
		int labelFontSize = labelFont.getSize() + 25;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		idOrdineLabel.setFont(newFont);
		idOrdineLabel.setForeground(Color.MAGENTA);
		
		idField = new JTextField();
		Font font = new Font("Arial", Font.PLAIN, 50);
		idField.setFont(font);
		
		confermaButton = new JButton("Conferma");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		confermaButton.setFont(buttonFont);
		confermaButton.setBackground(Color.WHITE);
		confermaButton.setForeground(Color.MAGENTA);
		
		indietro = new JButton("Torna In Gestione Trattamenti");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont3);
		indietro.setBackground(Color.WHITE);
		indietro.setForeground(Color.MAGENTA);
		
		ImageIcon icona = new ImageIcon("C:\\Users\\peppe\\eclipse-workspace\\GestioneRistorante\\logo.jpg");
        setIconImage(icona.getImage());
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("C:\\Users\\peppe\\eclipse-workspace\\GestioneRistorante\\sfondoarancione.jpg");
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
		
		
		setTitle("Elimina Trattamento");
		setSize(1920,1080);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		confermaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				PreparedStatement stmt = null;
				String sql = "DELETE FROM Trattamenti WHERE id = ?";
				
				try {
					String id_modifica = idField.getText().toString();
					
					Properties props = new Properties();
					FileInputStream in = new FileInputStream("database.propreties");

					props.load(in);
					in.close();


					String url = props.getProperty("url");
					String username = props.getProperty("username");
					String password = props.getProperty("password");
					conn = DriverManager.getConnection(url, username, password);
					
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, id_modifica);
					stmt.executeUpdate();
					
					idField.setText(" ");
					
					JOptionPane.showMessageDialog(null, "Trattamento cancellato correttamente");
					
					
				} catch(SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Trattamento non cancellato correttamente");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
				);
		
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Trattamenti();
				dispose();
			}
		}
				);
		
	}
	

}