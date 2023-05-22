package it.centroestetico;

import javax.swing.*;

import java.sql.*;
import java.util.Properties;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Staff1 extends JFrame {

	private JPanel panel;
	private JButton button,terminaButton;
	
	private JLabel labelVisualizza,idlabel;
	private JTextArea areaVisualizza;
	 private JCheckBox checkBox;
	 private JTextField idUtenteField;
	
	public Staff1()  {
	
		
	
		panel = new JPanel(new GridLayout(4,2));
		setTitle("Ara Personale");
		setSize(1920,1120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		checkBox = new JCheckBox("Check me!");
		
		button = new  JButton("Visualizza Calendario");
		
		terminaButton = new JButton("Lavoro Terminato");
		idUtenteField = new JTextField();
		
		labelVisualizza = new JLabel("**Mostra Calendario**");
		
		areaVisualizza = new JTextArea();
		
		idlabel=new JLabel("Inserisci Id Apuntamento");
		
		  areaVisualizza.setEditable(false);
		
			panel = new JPanel(new GridLayout(6,2)) {
		        @Override
		        protected void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            ImageIcon imageIcon = new ImageIcon("spa.jpg");
		            Image image = imageIcon.getImage();
		            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		        }
		    };
		  
		panel.add(idlabel);
	   panel.add(idUtenteField);
		panel.add(labelVisualizza);
		panel.add(areaVisualizza);
		panel.add(checkBox);
		panel.add(button);
		panel.add(terminaButton);
		button.setBackground(Color.decode("#A52A2A"));
		terminaButton.setBackground(Color.decode("#A52A2A"));
		
		areaVisualizza.setLineWrap(true);
		areaVisualizza.setEditable(false);

		// Crea lo JScrollPane che contiene l'area di testo

		JScrollPane	scroll = new JScrollPane(areaVisualizza);

		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panel.add(scroll);

		
		button.addActionListener(e->{
			
			areaVisualizza.setText("");
			Connection conn_staff = null;
			PreparedStatement stmt_staff = null;
			ResultSet rs_staff = null;
			
			try {
				
				Properties props = new Properties();
				FileInputStream in = new FileInputStream("database.propreties");
			
					props.load(in);
					in.close();
				
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				
				
				
			
				
			
				conn_staff = DriverManager.getConnection(url, username, password);
				
				String sql = "Select* from appuntamenti where id_staff= 1 And eseguito = false";
				stmt_staff=conn_staff.prepareStatement(sql);
				rs_staff=stmt_staff.executeQuery(sql);
				
				try {
					conn_staff = DriverManager.getConnection(url, username, password);
					stmt_staff=conn_staff.prepareStatement(sql);
					
					rs_staff =stmt_staff.executeQuery(sql);
					
					while(rs_staff.next()) {
						boolean eseguito=rs_staff.getBoolean("eseguito");
						String eseguito_stringa= "Lavoro In corso";
					
					    int id_appuntamento = rs_staff.getInt("id");
					    String nomeCliente = rs_staff.getString("nome");
					    String cognomeCliente = rs_staff.getString("cognome");
					    String numeroTelefonoCliente =rs_staff.getString("numero");
					    areaVisualizza.append("Id Appuntamento: " + id_appuntamento + "\nNome Cliente:" + nomeCliente + "\nCognome Cliente: " + cognomeCliente+"\nTelefono Cliente" + numeroTelefonoCliente + "\nTelefono Cliente" + "\nStato Trattamento: "+ "\nIn corso" +"\n\n");
					    System.out.println("Nome Cliente" + nomeCliente);
					  
					}


				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			
		});
		
		terminaButton.addActionListener(c->{
			 if (checkBox.isSelected()) {
				 try {
						Properties props = new Properties();
						FileInputStream in = new FileInputStream("database.propreties");
					
							props.load(in);
							in.close();
						
						
						String url = props.getProperty("url");
						String username = props.getProperty("username");
						String password = props.getProperty("password");
						
						
						
						
					
					Connection conn = DriverManager.getConnection(url, username, password);
					int idutente = Integer.parseInt(idUtenteField.getText());
					String sqlUpdate = "UPDATE appuntamenti SET eseguito = true WHERE id = ?";
					PreparedStatement stmt = conn.prepareStatement(sqlUpdate);
					stmt.setInt(1, idutente);
					stmt.executeUpdate();
		               JOptionPane.showMessageDialog(null,"Campo Modificato Correttamente");
		               
		               System.out.println("Ciao");
				} catch (SQLException | IOException e1) {
					
					e1.printStackTrace();
				}

	             idUtenteField.setText(""); 
	             areaVisualizza.validate();
			  }
		});
		
		add(panel);
		setVisible(true);
	}
	
	

}
