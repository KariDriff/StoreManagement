package com.quatrocentosquatro.storemanagement.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TelaManutencao extends JFrame {
    private JButton buttonHome;

    public TelaManutencao() {
        setTitle("Fase de Desenvolvimento");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Necessário para usar setBounds

        // Label informativa
        JLabel label = new JLabel("Em construção...", SwingConstants.CENTER);
        label.setBounds(50, 30, 300, 30);
        add(label);

        // Botão Home
        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 100, 100, 30);
        add(buttonHome);

        buttonHome.addActionListener(e -> {
            System.out.println("Abrir tela de Home");
            new TelaHome().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaManutencao().setVisible(true));
    }
}
