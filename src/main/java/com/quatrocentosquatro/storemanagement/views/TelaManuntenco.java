package com.quatrocentosquatro.storemanagement.views;

import javax.swing.*;
public class TelaManuntenco extends JFrame {

    public TelaManuntenco() {
        setTitle("Fase de Desenvolvimento");
        setSize(400, 200);
        setLocationRelativeTo(null); // Centraliza na tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Em construção...", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaManuntenco());
    }
}