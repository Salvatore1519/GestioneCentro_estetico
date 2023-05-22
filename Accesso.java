package it.centroestetico;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.Properties;

public class Accesso extends JFrame {

    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    JButton accediButton;

    private JPanel panel;

    public Accesso() {
        panel = new JPanel(new GridLayout(3, 1));

        panel = new JPanel(new GridLayout(6, 2)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("spa.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        usernameLabel = new JLabel("Inserisci Username");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Inserisci Password");
        passwordField = new JPasswordField();
        accediButton = new JButton("Accedi");

        accediButton.addActionListener(e -> {
            String nomeUtente = usernameField.getText();
            char[] passwordUtente = passwordField.getPassword();
            String passwordText = new String(passwordUtente);

            try {
                Properties props = new Properties();
                FileInputStream in = new FileInputStream("database.propreties");

                props.load(in);
                in.close();

                String url = props.getProperty("url");
                String username = props.getProperty("username");
                String password = props.getProperty("password");
                String sql = "SELECT * FROM accesso WHERE username = ? AND password = ?";

                try (Connection conn = DriverManager.getConnection(url, username, password);
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setString(1, nomeUtente);
                    stmt.setString(2, passwordText);

                    try (ResultSet rs = stmt.executeQuery()) {
                        boolean controllo = false;

                        while (rs.next()) {
                            int id_staff = rs.getInt("id");
                            String nomeConfronto = rs.getString("username");
                            String nomeStaff = rs.getString("nome_staff");
                            String cognomeStaff = rs.getString("cognome_staff");
                            String passwordConfronto = rs.getString("password");

                            System.out.println("Nome Staff: " + nomeStaff + " Cognome Staff: " + cognomeStaff);

                            if (nomeUtente.equals(nomeConfronto) && passwordConfronto.equals(passwordText)) {
                                System.out.println("Sono " + nomeStaff + " " + cognomeStaff);
                                System.out.println("Portale Numero " + id_staff);
                                controllo = true;

                                if (id_staff == 1) {
                                    new Staff1();
                                } else if (id_staff == 2) {
                                    new Staff2();
                                } else if (id_staff == 3) {
                                    new Admin();
                                } else if (id_staff == 4) {
                                    new MenuFrontOffice();
                                }
                            }
                        }

                        if (!controllo) {
                            JOptionPane.showMessageDialog(null, "Password o Username Errati");
                        }
                    }
                }
            } catch (SQLException | FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        accediButton.setBackground(Color.decode("#A52A2A"));
        panel.add(accediButton);

        add(panel);
        setTitle("LOGIN");
        setSize(1120, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Accesso();
    }

}
