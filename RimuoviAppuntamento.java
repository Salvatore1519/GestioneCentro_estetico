package it.centroestetico;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
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

public class RimuoviAppuntamento extends JFrame{
	
	private static final String DB_URL = "jdbc:mysql://localhost/centro_estetico";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";	
	
	private JLabel idAppuntamentoLabel;
	private JTextField idAppuntamentoField;
	private JButton eliminaButton,indietroButton;
	
	public RimuoviAppuntamento() {
		
		idAppuntamentoLabel = new JLabel("Inserisci ID dell'appuntamento da eliminare");
		idAppuntamentoField = new JTextField();
		
		eliminaButton = new JButton("Elimina Appuntamento");
		indietroButton = new JButton("Torna Al Calendario");
		
		JPanel panel = new JPanel(new GridLayout(2,2));
		panel.add(idAppuntamentoLabel);
		panel.add(idAppuntamentoField);
		panel.add(eliminaButton);
		panel.add(indietroButton);
		
		setTitle("RimuoviAppuntamenti");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1920, 1080);
	    
	    eliminaButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Connection conn = null;
	    		PreparedStatement stmt = null;
	    		String sql = "DELETE FROM appuntamenti WHERE id = ?";
	    		
	    		try {
	    			
					conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

	    			String idModifica1 = idAppuntamentoField.getText();
					int idModifica = Integer.parseInt(idModifica1);
					
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, idModifica);
					
					stmt.executeUpdate();
					
					
	    		}catch(SQLException e4) {
	    			e4.printStackTrace();
	    		}finally {
	    			try {
	    				stmt.close();
	    				conn.close();
	    			}catch(SQLException e5) {
	    				e5.printStackTrace();
	    			}
	    		}
	    	}
	    }
	    		);
	    
	    
	    
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















