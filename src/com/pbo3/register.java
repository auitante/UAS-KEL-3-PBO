package com.pbo3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public register() {
        // Set up the frame
        setTitle("Admin Registration");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerAdmin();
            }
        });

        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(registerButton);

        // Set the frame visibility
        setVisible(true);
    }

    private void registerAdmin() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // Melakukan koneksi ke database menggunakan kelas Conn
        try (Connection connection = Conn.connect()) {

            // Query untuk memasukkan data ke dalam tabel admin
            String query = "INSERT INTO admin (username, password) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                // Menjalankan query
                preparedStatement.executeUpdate();
                System.out.println("Admin registered successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Failed to register admin");
        }
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new register();
            }
        });
    }
}

