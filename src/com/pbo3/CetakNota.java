package com.pbo3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CetakNota extends JFrame {
    private JTextArea notaTextArea;

    public CetakNota() {
        setTitle("Cetak Nota");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        notaTextArea = new JTextArea();
        notaTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(notaTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton cetakButton = new JButton("Cetak Nota");
        cetakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cetakNota();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cetakButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setCetakNotaText(String text) {
        notaTextArea.setText(text);
    }

    private void cetakNota() {
        String notaText = notaTextArea.getText();
        // Implement logic to print the nota text, e.g., using a printer or file output.
        // This logic will depend on your specific requirements.
        // You can use libraries like Java Print Service for printing.
    }
}

