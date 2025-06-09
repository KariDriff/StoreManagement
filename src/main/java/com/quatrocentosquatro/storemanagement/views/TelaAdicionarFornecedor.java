package com.quatrocentosquatro.storemanagement.views;

import com.quatrocentosquatro.storemanagement.controller.GerenciarFornecedores;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author Everton Ferreira
 */
public class TelaAdicionarFornecedor extends JFrame {
    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextField campoCnpj;
    private JButton buttonAdicionar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    public TelaAdicionarFornecedor() {
        setTitle("Adicionar Fornecedor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campo Nome
        campoNome = new JTextField("Nome");
        campoNome.setBounds(30, 30, 160, 30);
        campoNome.addMouseListener(getPlaceholderClearListener(campoNome, "Nome"));
        add(campoNome);

        // Campo Telefone
        campoTelefone = new JTextField("Telefone");
        campoTelefone.setBounds(210, 30, 160, 30);
        campoTelefone.addMouseListener(getPlaceholderClearListener(campoTelefone, "Telefone"));
        add(campoTelefone);

        // Campo Email
        campoEmail = new JTextField("Email");
        campoEmail.setBounds(390, 30, 160, 30);
        campoEmail.addMouseListener(getPlaceholderClearListener(campoEmail, "Email"));
        add(campoEmail);

        // Campo CNPJ
        campoCnpj = new JTextField("CNPJ");
        campoCnpj.setBounds(30, 80, 160, 30);
        campoCnpj.addMouseListener(getPlaceholderClearListener(campoCnpj, "CNPJ"));
        add(campoCnpj);

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);
        buttonVoltar.addActionListener(e -> {
            new TelaFornecedor().setVisible(true);
            dispose();
        });

        // Botão Home
        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);
        buttonHome.addActionListener(e -> {
            new TelaHome().setVisible(true);
            dispose();
        });

        // Botão Adicionar
        buttonAdicionar = new JButton("Adicionar");
        buttonAdicionar.setBounds(390, 300, 120, 30);
        add(buttonAdicionar);
        buttonAdicionar.addActionListener(e -> {
            String nome = campoNome.getText();
            String telefone = campoTelefone.getText();
            String email = campoEmail.getText();
            String cnpj = campoCnpj.getText();

            GerenciarFornecedores gerenciador = new GerenciarFornecedores();
            gerenciador.adicionarFornecedor(nome, telefone, email, cnpj);

            System.out.println("Fornecedor adicionado com sucesso!");
            new TelaFornecedor().setVisible(true);
            dispose();
        });
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
        SwingUtilities.invokeLater(() -> new TelaAdicionarFornecedor().setVisible(true));
    }
}
