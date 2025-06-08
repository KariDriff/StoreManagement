package com.quatrocentosquatro.storemanagement.views;

import java.awt.event.ActionEvent;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;


public class TelaResgistrarEntrada extends JFrame {
    private JTextField campoId;
    private JTextField campotipo;
    private JTextField campovalor;
    private JTextField campodescricao;
    private JTextField campodataHora;
    private JButton buttonRegistrar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    public TelaResgistrarEntrada() {
        setTitle("Registrar Entrada");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        campoId = new JTextField("Id");
        campoId.setBounds(30, 30, 160, 30);
        campoId.addMouseListener(getPlaceholderClearListener(campoId, "Id"));
        add(campoId);

        campotipo = new JTextField("Tipo");
        campotipo.setBounds(210, 30, 160, 30);
        campotipo.addMouseListener(getPlaceholderClearListener(campotipo, "Tipo"));
        add(campotipo);

        campovalor = new JTextField("Valor");
        campovalor.setBounds(390, 30, 160, 30);
        campovalor.addMouseListener(getPlaceholderClearListener(campovalor, "Valor"));
        add(campovalor);

        campodescricao = new JTextField("Quantidade");
        campodescricao.setBounds(30, 80, 160, 30);
        campodescricao.addMouseListener(getPlaceholderClearListener(campodescricao, "Descrição"));
        add(campodescricao);

        campodataHora = new JTextField("Data Hora");
        campodataHora.setBounds(210, 80, 160, 30);
        campodataHora.addMouseListener(getPlaceholderClearListener(campodataHora, "Data Hora"));
        add(campodataHora);

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);

        buttonVoltar.addActionListener((ActionEvent e) -> {
            System.out.println("Abrir tela de Financeiro");
            new TelaFinanceiro().setVisible(true);
            dispose();
        });

        // Botão Home
        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);

        buttonHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir tela de Home");
                new TelaHome().setVisible(true);
                dispose();
            }
        });

         // Botão Cadastrar
        buttonRegistrar = new JButton("Registrar");
        buttonRegistrar.setBounds(390, 300, 120, 30);
        add(buttonRegistrar);
    }

    private MouseAdapter getPlaceholderClearListener(JTextField field, String placeholder) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                }
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaResgistrarEntrada().setVisible(true));
    }
}
