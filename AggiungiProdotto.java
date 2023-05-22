package it.centroestetico;

 
import javax.swing.*;
import java.sql.*;
import java.util.Properties;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.*;


public class AggiungiProdotto extends JFrame{

	private JLabel statusLabel;
	private JTextField prezzoField,categoriaField,portataField;

	private JLabel prezzoLabel, portataLabel,categoriaLabel;

	

	private JButton inserisciButton,indietro,inviaButton,aggiungiFotoBtn;
	JTextArea area ;
	JPanel panel;


	public AggiungiProdotto() throws IOException {

		super("Image Uploader");

		Properties props = new Properties();
		FileInputStream in = new FileInputStream("database.propreties");
	
			props.load(in);
			in.close();
		
		
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		
		
		
		
	
		
	
		
		
		area = new JTextArea();
		ImageIcon icona = new ImageIcon("\"C:\\Users\\Salvatore\\Desktop\\immagini_centro_estetico\\icona.png\"");
        setIconImage(icona.getImage());
		setTitle("Aggiungi Al Menu");
		setSize(1680,1050);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\sfondoarancione.jpg");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridLayout(10,2));
	

		portataField= new JTextField();
		Font font = new Font("Arial", Font.PLAIN, 20);
		portataField.setFont(font);
		
		portataLabel = new JLabel("Nome Prodotto " );
		Font labelFont = portataLabel.getFont();
		int labelFontSize = labelFont.getSize() + 35;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		portataLabel.setFont(newFont);

		

		
	
	
	


		prezzoField = new JTextField();
		Font font3 = new Font("Arial", Font.PLAIN, 50);
		prezzoField.setFont(font3);

		

		prezzoField = new JTextField();


		prezzoLabel= new JLabel("Inserisci Prezzo");
		Font labelFont4 = prezzoLabel.getFont();
		int labelFontSize4 = labelFont4.getSize() + 35;
		Font newFont5 = new Font(labelFont4.getName(), labelFont4.getStyle(), labelFontSize4);
		prezzoLabel.setFont(newFont5);


	



		panel.setBackground(Color.white);

		indietro = new JButton("Torna al menu Staff");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont);

		inserisciButton = new JButton("Conferma Password");
		inserisciButton.setBackground(Color.ORANGE);
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		inserisciButton.setFont(buttonFont2);


		inviaButton = new JButton("AggiungiFoto");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		inviaButton.setFont(buttonFont3);
		inviaButton.setBackground(Color.decode("#A52A2A"));
		indietro.setBackground(Color.decode("#A52A2A"));
		
		
		panel = new JPanel(new GridLayout(6,2)) {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon imageIcon = new ImageIcon("spa.jpg");
	            Image image = imageIcon.getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	        }
	    };
		
		
		
		

		panel.add(portataLabel);
		
		panel.add(portataField);
		
		panel.add(prezzoLabel);
		panel.add(prezzoField);
		
		panel.add(inviaButton);
		
		
		panel.add(indietro);

		





		inviaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {
				String portate =  portataField.getText();
			
				double prezzo= (double) Double.parseDouble(prezzoField.getText());

				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {

					
					conn = DriverManager.getConnection(url, username, password);
						String sql_editore = "Insert INTO prodotti(nome_prodotto,prezzo_prodotto) VALUES(?,?)";
						
						
						stmt=conn.prepareStatement(sql_editore);

					
						stmt.setString(1, portate);
						stmt.setDouble(2,prezzo);
						
						stmt.execute();

					}catch(Exception  e ) {
						e.printStackTrace();
					}

				
				try {
					stmt.close();
					conn.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
					

				
				try {
					new CaricaImmagini();
				} catch (IOException e) {
					
					e.printStackTrace();
				}

			}
		
		});
		
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestioneProdotti();
				dispose();
			}
		}
				);



		add(panel);
		setVisible(true);
	

	
	}

		



}
