package it.centroestetico;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AggiornaAppuntamento extends JFrame{
	
	private static final String DB_URL = "jdbc:mysql://localhost/centro_estetico";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";	
	
	private JLabel idModificaLabel,idStaffLabel,nomeLabel,cognomeLabel,numeroLabel,dataAppuntamentoLabel,eseguitoLabel,orarioLabel,trattamentoLabel;
	private JTextField idModificaField,idStaffField,nomeField,cognomeField,numeroField,dataAppuntamentoField,eseguitoField,orarioField,trattamentoField;
	
	private JButton aggiornaButton,indietroButton; 
	
	public AggiornaAppuntamento() {
		
		idModificaLabel = new JLabel("Inserisci ID Appuntamento da modificare");
		idModificaField = new JTextField();
		
		idStaffLabel = new JLabel("Inserisci ID Staff");
		idStaffField = new JTextField();
		
		nomeLabel = new JLabel("Inserisci Nome Cliente");
		nomeField = new JTextField();
		
		cognomeLabel = new JLabel("Inserisci Cognome Cliente");
		cognomeField = new JTextField();
		
		numeroLabel = new JLabel("Inserisci Numero Cliente");
		numeroField = new JTextField();
		
		dataAppuntamentoLabel = new JLabel("Inserisci la data dell'appuntamento(aaaa/mm/gg");
		dataAppuntamentoField = new JTextField();
		
		eseguitoLabel = new JLabel("Appuntamento Eseguito?(true or false)");
		eseguitoField = new JTextField();
		
		orarioLabel = new JLabel("Orario Nuovo:");
		orarioField = new JTextField();
		
		trattamentoLabel = new JLabel("Trattamento Nuovo");
		trattamentoField = new JTextField();
		
		aggiornaButton = new JButton("Aggiorna Appuntamento");
		indietroButton = new JButton("Torna Al Calendario");
		
		JPanel panel = new JPanel(new GridLayout(10,2));
		panel.add(idModificaLabel);
		panel.add(idModificaField);
		panel.add(idStaffLabel);
		panel.add(idStaffField);
		panel.add(nomeLabel);
		panel.add(nomeField);
		panel.add(cognomeLabel);
		panel.add(cognomeField);
		panel.add(numeroLabel);
		panel.add(numeroField);
		panel.add(dataAppuntamentoLabel);
		panel.add(dataAppuntamentoField);
		panel.add(eseguitoLabel);
		panel.add(eseguitoField);
		panel.add(orarioLabel);
		panel.add(orarioField);
		panel.add(trattamentoLabel);
		panel.add(trattamentoField);
		panel.add(aggiornaButton);
		panel.add(indietroButton);
		
		setTitle("AggiornAppuntamenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1920, 1080);
		
	    aggiornaButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Connection conn = null;
	            PreparedStatement stmt = null;
	            String sql = "UPDATE appuntamenti SET id_Staff = ?, nome = ?, cognome = ?, numero = ?, data_appuntamento = ?, eseguito = ?, orario = ?, trattamento = ? WHERE id = ?";
	            try {
	                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	                stmt = conn.prepareStatement(sql);

	                String id_staff = idStaffField.getText();
	                int idStaff = Integer.parseInt(id_staff);

	                String nome = nomeField.getText();
	                String cognome = cognomeField.getText();
	                String numero = numeroField.getText();
	                String data = dataAppuntamentoField.getText();

	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                java.util.Date parsedDate = dateFormat.parse(data);
	                java.sql.Date date = new java.sql.Date(parsedDate.getTime());

	                String eseguito = eseguitoField.getText();
	                boolean eseguito1 = Boolean.parseBoolean(eseguito);
	                
	                String orario = orarioField.getText();
	            	LocalTime time = LocalTime.parse(orario);
	                java.sql.Time timeSQL = java.sql.Time.valueOf(time);
	                
	                String trattamento = trattamentoField.getText();
	                
	                String idModifica1 = idModificaField.getText();
	                int idModifica = Integer.parseInt(idModifica1);

	                stmt.setInt(1, idStaff);
	                stmt.setString(2, nome);
	                stmt.setString(3, cognome);
	                stmt.setString(4, numero);
	                stmt.setDate(5, date);
	                stmt.setBoolean(6, eseguito1);
	                stmt.setTime(7, timeSQL);
	                stmt.setString(8, trattamento);
	                stmt.setInt(9, idModifica);

	                int righeInserite = stmt.executeUpdate();
	                if (righeInserite > 0) {
	                    JOptionPane.showMessageDialog(null, "Appuntamento aggiornato correttamente");
	                }
	                
	                idModificaField.setText("");
	                idStaffField.setText("");
	                nomeField.setText("");
	                cognomeField.setText("");
	                numeroField.setText("");
	                dataAppuntamentoField.setText("");
	                eseguitoField.setText("");
	                orarioField.setText("");
	                trattamentoField.setText("");

	            } catch (SQLException | ParseException e1) {
	                e1.printStackTrace();
	            } finally {
	                try {
	                    if (stmt != null) {
	                        stmt.close();
	                    }
	                    if (conn != null) {
	                        conn.close();
	                    }
	                } catch (SQLException e3) {
	                    e3.printStackTrace();
	                }
	            }
	        }
	    });
	    
	    
	    
	    
	    
	    indietroButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		new CalendarioGUI();
	    		dispose();
	    	}
	    }
	    		);
	    
	    
	    add(panel);
	    setVisible(true);
	}

	
}











