package it.centroestetico;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarioEventi extends JFrame {

    private JCalendar calendario;
    private JTextArea eventiArea;
    private JButton aggiungiEventoButton;

    private Map<Date, String> eventi;

    public CalendarioEventi() {
        setTitle("Calendario Eventi");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creazione del calendario
        calendario = new JCalendar();

        // Creazione dell'area di testo per gli eventi
        eventiArea = new JTextArea();
        eventiArea.setEditable(false);

        // Creazione del bottone per l'aggiunta di eventi
        aggiungiEventoButton = new JButton("Aggiungi Evento");

        // Inizializzazione della mappa degli eventi
        eventi = new HashMap<>();
       
        calendario.setDecorationBordersVisible(false);
        calendario.setDecorationBackgroundColor(Color.WHITE);
        // Aggiunta del listener per il click sul bottone di aggiunta evento
        aggiungiEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea un nuovo frame per l'inserimento dell'evento
                JFrame nuovoEventoFrame = new JFrame("Aggiungi Evento");
                nuovoEventoFrame.setSize(300, 200);
                nuovoEventoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Crea i componenti per l'inserimento dell'evento
                JLabel dataLabel = new JLabel("Data: " + calendario.getDate());
                JLabel titoloLabel = new JLabel("Titolo:");
                JTextField titoloField = new JTextField();
                JLabel descrizioneLabel = new JLabel("Descrizione:");
                JTextArea descrizioneArea = new JTextArea();
                JScrollPane descrizioneScrollPane = new JScrollPane(descrizioneArea);
                JButton confermaButton = new JButton("Conferma");
                JButton annullaButton = new JButton("Annulla");

                // Aggiunge i componenti al layout del nuovo frame
                JPanel nuovoEventoPanel = new JPanel(new GridLayout(5, 1));
                nuovoEventoPanel.add(dataLabel);
                nuovoEventoPanel.add(titoloLabel);
                nuovoEventoPanel.add(titoloField);
                nuovoEventoPanel.add(descrizioneLabel);
                nuovoEventoPanel.add(descrizioneScrollPane);
                nuovoEventoPanel.add(confermaButton);
                nuovoEventoPanel.add(annullaButton);
                nuovoEventoFrame.add(nuovoEventoPanel);

                // Aggiunge il listener per il click sul bottone di conferma
                confermaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Ottiene i dati inseriti dall'utente
                        String titolo = titoloField.getText();
                        String descrizione = descrizioneArea.getText();
                        Date data = calendario.getDate();

                        // Aggiunge l'evento alla mappa degli eventi
                        eventi.put(data, titolo + ": " + descrizione);

                        // Aggiorna l'area di testo degli eventi
                        aggiornaAreaEventi();
                        nuovoEventoFrame.dispose();
                    }
                });

                // Aggiunge il listener per il click sul bottone di annulla
                annullaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nuovoEventoFrame.dispose();
                    }
                });

                // Mostra il nuovo frame per l'inserimento dell'evento
                nuovoEventoFrame.setVisible(true);
            }
        });

        // Aggiunta del calendario e dell'area di testo degli eventi al frame principale
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(calendario, BorderLayout.WEST);
        panel.add(eventiArea, BorderLayout.CENTER);
        panel.add(aggiungiEventoButton, BorderLayout.SOUTH);
        add(panel);

        // Aggiornamento dell'area di testo degli eventi
        aggiornaAreaEventi();
    }

    // Metodo per l'aggiornamento dell'area di testo degli eventi
    private void aggiornaAreaEventi() {
        // Crea un nuovo formatter per la data
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Ottiene la lista delle date degli eventi
        Set<Date> dateEventi = eventi.keySet();

        // Ordina le date degli eventi
        ArrayList<Date> listaDateEventi = new ArrayList<>(dateEventi);
        Collections.sort(listaDateEventi);

        // Aggiorna l'area di testo degli eventi
        eventiArea.setText("");
        for (Date data : listaDateEventi) {
            String evento = eventi.get(data);
            eventiArea.append(formatter.format(data) + " - " + evento + "\n");
        }
    }

    public static void main(String[] args) {
        CalendarioEventi calendarioEventi = new CalendarioEventi();
        calendarioEventi.setVisible(true);
    }
}