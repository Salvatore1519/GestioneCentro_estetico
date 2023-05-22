package it.centroestetico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Appuntamenti {
	
	private static final String DB_URL = "jdbc:mysql://localhost/centro_estetico";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";
	
    private LocalTime ora2;
    private String nome,cognome,numero;
    private int idStaff;
    //private boolean eseguito1;
    private Date dataAppuntamento;
    private String trattamento;

    public Appuntamenti(LocalTime ora2, String nome, String cognome, String numero, int idStaff, Date dataAppuntamento, String trattamento) {
        this.ora2 = ora2;
        this.nome = nome;
        this.cognome = cognome;
        this.numero = numero;
        this.idStaff = idStaff;
        //this.eseguito1 = eseguito1;
        this.dataAppuntamento = dataAppuntamento;
        this.trattamento = trattamento;
    }

    public LocalTime getOra() {
        return ora2;
    }

    public String getNome() {
        return nome;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public int getIdStaff() {
    	return idStaff;
    }
    
    
    public Date getData() {
    	return dataAppuntamento;
    }
    
    public String getTrattamento() {
    	return trattamento;
    }
    
    public String getAppuntamento() {
	    String st = "";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM appuntamenti";
	    
	    try {
	        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        stmt = conn.prepareStatement(sql);
	        
	        rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            String nome = rs.getString("nome");
	            String cognome = rs.getString("cognome");
	            String numero = rs.getString("numero");
	            int idStaff = rs.getInt("idStaff");
	            //boolean eseguito = rs.getBoolean("eseguito");
	            Date data = rs.getDate("data_appuntamento");
	            Time ora = rs.getTime("orario");
	            String trattamento = rs.getString("trattamento");
	            st += nome + " " + cognome + " " + numero + " " + idStaff  + " " + data + " " + ora + " " + trattamento;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return st;
	}
}










