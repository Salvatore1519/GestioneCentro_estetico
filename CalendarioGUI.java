package it.centroestetico;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import java.sql.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JCalendar;

public class CalendarioGUI extends JFrame {
	
	private static final String DB_URL = "jdbc:mysql://localhost/centro_estetico";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";
	
    private Calendario calendario;
    private JTextArea txtArea;
    private JCalendar jCalendar;
    private JTextField txtOra;
    private JButton aggiungiButton,aggiornaButton,rimuoviButton,indietroButton,pagamentiButton,visualizzaButton;

    public CalendarioGUI() {
        calendario = new Calendario();
        initializeUI();
    }

    private void initializeUI() {
    	
    	 
         
         aggiornaButton = new JButton("Modifica Appuntamento");
         aggiornaButton.setBackground(Color.white);
         Font buttonFont2 = new Font("Arabic", Font.BOLD, 25);
 		aggiornaButton.setFont(buttonFont2);
         
         rimuoviButton = new JButton("Rimuovi Appuntamento");
         rimuoviButton.setBackground(Color.white);
         Font buttonFont3 = new Font("Arabic", Font.BOLD, 25);
 		rimuoviButton.setFont(buttonFont3);
         
         indietroButton = new JButton("Torna Indietro");
         indietroButton.setBackground(Color.white);
         Font buttonFont4 = new Font("Arabic", Font.BOLD, 25);
 		indietroButton.setFont(buttonFont4);
 		
 		 visualizzaButton = new JButton("Visualizza");
         visualizzaButton.setBackground(Color.white);
          Font buttonFont5 = new Font("Arabic", Font.BOLD, 25);
  		visualizzaButton.setFont(buttonFont5);
 		
 		
         
         JPanel panel2 = new JPanel(new GridLayout(1,4));
         //panel2.add(aggiungiButton);
         panel2.add(aggiornaButton);
         panel2.add(rimuoviButton);
         panel2.add(indietroButton);
         panel2.add(visualizzaButton);
    	
    	
        setTitle("Calendario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton btnAggiungi = new JButton("Aggiungi Appuntamento");
        btnAggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					aggiungiAppuntamento();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        
        
        panel.add(btnAggiungi, BorderLayout.NORTH);

        jCalendar = new JCalendar();
        panel.add(jCalendar, BorderLayout.CENTER);

        JPanel pnlOra = new JPanel(new FlowLayout());
        pnlOra.add(new JLabel("Ora:"));
        txtOra = new JTextField(10);
        pnlOra.add(txtOra);
        panel.add(pnlOra, BorderLayout.SOUTH);

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        //txtArea.setPreferredSize(new Dimension(200, 200));
        JScrollPane scrollPane = new JScrollPane(txtArea);
        panel.add(scrollPane, BorderLayout.SOUTH);
        add(pnlOra, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        
        indietroButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new MenuFrontOffice();
        	}
        }
        		);
        
        aggiornaButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AggiornaAppuntamento();
        		dispose();
        	}
        }
        		);
        
        rimuoviButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new RimuoviAppuntamento();
        		dispose();
        	}
        }
        		);
        
        
        // Metodo per visualizzare gli appuntamenti prenotati 
        visualizzaButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String st = "";
        	   txtArea.setText("");
  
        		    Connection conn = null;
        		    PreparedStatement stmt = null;
        		    ResultSet rs = null;
        		    String sql = "SELECT * FROM appuntamenti where data_appuntamento =?";
        		    
        		    LocalDate data = jCalendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	        String dataString = data.format(formatter);
        	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	      
        		    try {
        		    	String date = dataString;
                        java.util.Date parsedDate = dateFormat.parse(date);
                        java.sql.Date dataAppuntamento = new java.sql.Date(parsedDate.getTime());
        		       
                        
                        
                        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        		        stmt = conn.prepareStatement(sql);
        		        stmt.setDate(1, dataAppuntamento);
        		        rs = stmt.executeQuery();
        		        
        		        while (rs.next()) {
        		        	String lavorazione_trattamento= "In Corso";
        		            String nome = rs.getString("nome");
        		            String cognome = rs.getString("cognome");
        		            String numero = rs.getString("numero");
        		            int idStaff = rs.getInt("id_staff");
        		            boolean eseguito = rs.getBoolean("eseguito");
        		            if(eseguito) {
        		            	lavorazione_trattamento="TRATTAMENTO FINITO";
        		            }
        		            Date data_rs = rs.getDate("data_appuntamento");
        		            Time ora = rs.getTime("orario");
        		            String trattamento = rs.getString("trattamento");
        		            st += "Nome: " +nome + " " + "Cognome:" + cognome + "Numero Telefono Cliente:" + numero + "Cliente Assegnato al Tablet " + idStaff + " Stato Trattamento: " + lavorazione_trattamento + " Data-Appuntamento" + data + ": Ora" + ora + "Tipo Trattamento Effettuato " + trattamento + "\n";
        		        }
        		    } catch (SQLException e1) {
        		        e1.printStackTrace();
        		    } catch (ParseException e2) {
						
						e2.printStackTrace();
					} finally {
        		        try {
        		            if (rs != null) rs.close();
        		            if (stmt != null) stmt.close();
        		            if (conn != null) conn.close();
        		        } catch (SQLException e1) {
        		            e1.printStackTrace();
        		        }
        		    }
        		    txtArea.append(st);
        	}
        }
        		);
        
    }
   
     // Metodo per l'aggiunta di un appuntamento
    private void aggiungiAppuntamento() throws SQLException {
    	
    	String sql = "INSERT INTO appuntamenti(id_staff, nome, cognome, numero, data_appuntamento, orario, trattamento,eseguito) VALUES (?, ?, ?, ?, ?, ?, ?,0)";
    	String sql_clienti = "INSERT INTO clienti(nome,cognome,numero,trattamenti_effettuati) VALUES(?,?,?,?)";
    	Connection conn2 =DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    	Connection conn3 =DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement stmt2 =conn2.prepareStatement(sql);
        PreparedStatement stmt_clienti = conn3.prepareStatement(sql_clienti);
        
        
    	
        LocalDate data = jCalendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataString = data.format(formatter);
        String oraString = txtOra.getText();
        
     

        try {
        	
        	String sql_select = "SELECT nome_trattamento FROM trattamenti";
        	
        	Connection conn_select = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	//Connection conn_clienti = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	PreparedStatement stmt_select = conn_select.prepareStatement(sql_select);
        	ResultSet rs_select = stmt_select.executeQuery();
        	
        	
        	
        		 // Arraylist dei trattamenti dispnibili, e caricamento al 'interno della select di selezione
        	List<String> trattamentiList = new ArrayList<>(); // Lista per memorizzare i nomi dei trattamenti

        	while (rs_select.next()) {
        	    String trattamento = rs_select.getString("nome_trattamento");
        	    trattamentiList.add(trattamento);
        	}

        	String[] options = trattamentiList.toArray(new String[0]);

        	JComboBox<String> selectList = new JComboBox<>(options);
        	int result = JOptionPane.showOptionDialog(null, selectList, "Seleziona Trattamento", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        	
        	String selectedOption = "";
        	if (result == JOptionPane.OK_OPTION) {
        	    selectedOption = (String) selectList.getSelectedItem();
        	    System.out.println("Selected option: " + selectedOption);
        	}

        	rs_select.close();
        	stmt_select.close();
        	conn_select.close();
        	
        	
        	
        	
            
            
             // Inserimento dei campi necessari per l'aggiunta del appuntamento  dalla UI alll interno del DB.
            String idStafff = JOptionPane.showInputDialog("Inserisci ID Tablet");
            int idStaff = Integer.parseInt(idStafff);
            
            String nome = JOptionPane.showInputDialog("Inserisci Il Nome del Cliente");
        	String cognome = JOptionPane.showInputDialog("Inserisci Cognome del Cliente");
        	String numero = JOptionPane.showInputDialog("Inserisci il Numero del Cliente");
        	String date = dataString;
        	String orario = txtOra.getText();
        	LocalTime time = LocalTime.parse(orario);
            java.sql.Time timeSQL = java.sql.Time.valueOf(time);
        	

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(date);
            java.sql.Date dataAppuntamento = new java.sql.Date(parsedDate.getTime());
            String trattamento = selectedOption;
            
            String nome2 = nome;
            String cognome2 = cognome;
            String telefono = numero;
            String trattamento2 = trattamento;
            
           
            
          

            stmt2.setInt(1, idStaff);
            stmt2.setString(2, nome);
            stmt2.setString(3, cognome);
            stmt2.setString(4, numero);
            stmt2.setDate(5, dataAppuntamento);
            //stmt2.setBoolean(6, eseguito1);
            stmt2.setTime(6, timeSQL);
            stmt2.setString(7, trattamento);
            
            stmt_clienti.setString(1, nome2);
            stmt_clienti.setString(2, cognome2);
            stmt_clienti.setString(3, telefono);
            stmt_clienti.setString(4, trattamento2);
            
            stmt_clienti.execute();
            
            LocalTime ora2 = null;
    		Appuntamento appuntamento = new Appuntamento(ora2, nome, cognome, numero, idStaff, dataAppuntamento, trattamento);
            calendario.aggiungiAppuntamento(appuntamento);
            //aggiornaTxtArea();

            int righeInserite = stmt2.executeUpdate();
            if (righeInserite > 0) {
                JOptionPane.showMessageDialog(null, "Appuntamento inserito correttamente");
            }
            
            System.out.println(nome2);
            
            
            
        } catch (SQLException  |NumberFormatException | StringIndexOutOfBoundsException | ParseException e) {
        	e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ora non valida. Utilizzare il formato HH:mm", "Errore", JOptionPane.ERROR_MESSAGE);
        }finally {
        	try {
        		
        		
	            if (stmt2 != null) stmt2.close();
				if (stmt_clienti != null) stmt_clienti.close();
	            if (conn2 != null) conn2.close();
	            if (conn3 != null) conn3.close();
        		
        	}catch(SQLException  |NumberFormatException | StringIndexOutOfBoundsException e) {
        		e.printStackTrace();
        	}
        }
    }

 
}