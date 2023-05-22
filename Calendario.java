package it.centroestetico;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Calendario {
    private List<Appuntamento> appuntamenti;

    public Calendario() {
        appuntamenti = new ArrayList<>();
    }

    public void aggiungiAppuntamento(Appuntamento appuntamento) {
        appuntamenti.add(appuntamento);
    }

    public List<Appuntamento> getAppuntamenti() {
        return appuntamenti;
    }
}