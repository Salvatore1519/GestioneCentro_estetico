package it.centroestetico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
//import org.apache.commons.
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;




public class Fattura extends JFrame{
	
	private static final String DB_URL = "jdbc:mysql://localhost/centro_estetico";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";	
	
	private JLabel nomeLabel,cognomeLabel,dataLabel;
	private JTextField nomeField,cognomeField,dataField;
	private JButton ricerca;
	
	public Fattura()  {
		
		nomeLabel = new JLabel("Inserisci Nome:");
		nomeField = new JTextField();
		
		cognomeLabel = new JLabel("Inserisci Cognome");
		cognomeField = new JTextField();
		
		dataLabel = new JLabel("Inserisci la data(aaaa-mm-gg)");
		dataField = new JTextField();
		
		ricerca = new JButton("Ricerca Cliente");
		
		JPanel panel = new JPanel(new GridLayout(4,2));
		panel.add(nomeLabel);
		panel.add(nomeField);
		panel.add(cognomeLabel);
		panel.add(cognomeField);
		panel.add(dataLabel);
		panel.add(dataField);
		panel.add(ricerca);
		
		setTitle("Fattura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1920, 1080);
		
		
	ricerca.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ex) {
			try {
				
				ArrayList<String> cliente = new ArrayList<String>();
				ArrayList<Double> prezzo = new ArrayList<Double>();
				ArrayList<Date> dataAppuntamento = new ArrayList<Date>();
				
				String sql_select = "select trattamenti.prezzo, appuntamenti.nome, appuntamenti.cognome, trattamenti.nome_trattamento, appuntamenti.data_appuntamento FROM trattamenti JOIN appuntamenti ON trattamenti.nome_trattamento = appuntamenti.trattamento WHERE appuntamenti.trattamento = trattamenti.nome_trattamento AND appuntamenti.data_appuntamento = ?  AND appuntamenti.nome = ? AND appuntamenti.cognome = ?";
				Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql_select);
				
				
				String nomeDaConfrontare = nomeField.getText();
				String cognomeDaConfrontare = cognomeField.getText();
				
				String dataDaConfrontare = dataField.getText();
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date parsedDate = dateFormat.parse(dataDaConfrontare);
	            java.sql.Date date = new java.sql.Date(parsedDate.getTime());
				
	            stmt.setDate(1, date);
	            stmt.setString(2, nomeDaConfrontare);
	            stmt.setString(3, cognomeDaConfrontare);
	            
	            stmt.execute();
	            ResultSet rs = stmt.executeQuery();
	            
	            int totale = 0;
	            String st = "";
	            
	            
				//contentStream.beginText(); 
				
	            PDDocument document = new PDDocument();
				PDPage page = new PDPage();
				document.addPage(page);
				PDPageContentStream contentStream = new PDPageContentStream(document,page);
				contentStream.beginText();
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				contentStream.setLeading(20);
				contentStream.newLineAtOffset(50, 700);
	            
				while(rs.next()) {
					
					double prezzo_trattamento = rs.getDouble("prezzo");
					String nome_cliente = rs.getString("nome");
					String cognome_cliente = rs.getString("cognome");
					String nome_trattamento = rs.getString("nome_trattamento");
					Date data = rs.getDate("data_appuntamento");
					
					
					cliente.add(nome_cliente + " " + cognome_cliente + " " +nome_trattamento);
					prezzo.add(prezzo_trattamento);
					dataAppuntamento.add(data);
					
					/*
					String clienti = "";
					for(String cliente1 : cliente) {
						clienti += cliente1;
						
					}
					
					Double prezzoTrattamento = 0.0;
					for(Double prezzo1: prezzo) {
						prezzoTrattamento += prezzo1;
					}
					
					Date dataAppuntamento1;
					for(Date data1: dataAppuntamento) {
						dataAppuntamento1 = data1;
					}
					
					System.out.println(clienti);
					*/
					
					double somma = 0.0;
					double totaleFinale = 0.0;
					for(Double prezzo1: prezzo) {
						somma += prezzo1;
						totaleFinale = somma;
					}
					
					Date dataAppuntamento1;
					for(Date data1: dataAppuntamento) {
						dataAppuntamento1 = data1;
					}
					
					
		            if (!cliente.isEmpty()) {
		                String ultimoCliente = cliente.get(cliente.size() - 1);
		                System.out.println(ultimoCliente);
		                contentStream.showText(ultimoCliente);
		                contentStream.newLine();
		            }
		            
		            if(!prezzo.isEmpty()) {
		            	double totale_prezzo = prezzo.get(prezzo.size() - 1);
		            	String totalePrezzo = Double.toString(totale_prezzo);
		            	System.out.println(totale_prezzo);
		            	contentStream.showText(totalePrezzo);
		                contentStream.newLine();
		            }
		            
		            if(!dataAppuntamento.isEmpty()) {
		            	Date data_finale = dataAppuntamento.get(dataAppuntamento.size() - 1);
		            	System.out.println(data_finale);
		            	String date1 = dateFormat.format(data_finale);
		            	contentStream.showText(date1);
		                contentStream.newLine();
		            }
		            System.out.println(totaleFinale);
		            String totaleFinale1 = Double.toString(totaleFinale);
		            contentStream.showText(totaleFinale1);
		            contentStream.newLine();
					//totale += prezzo_trattamento;
					
					//System.out.println(totale + " " + nome_cliente + " " + cognome_cliente + " " + nome_trattamento + " " + data);
					//st += "Fattura Cliente : " + nome_cliente + " " + cognome_cliente + " " + nome_trattamento + " " + totale;
					
					
				}
				 
	             
	             contentStream.endText();
	             contentStream.close();
	             document.save("C:\\Users\\Salvatore\\Desktop\\immagini_centro_estetico\\fattura.pdf");
	             document.close();
				
	            
	            
				
				
				
			}catch(IOException e) {
				e.printStackTrace();
			}catch(SQLException e1) {
				e1.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			);	
		
		
		

	    add(panel);
	    setVisible(true);
		
	}

	
	
}
