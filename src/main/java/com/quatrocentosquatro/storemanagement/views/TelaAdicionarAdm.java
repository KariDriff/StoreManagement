package com.quatrocentosquatro.storemanagement.views;

import com.quatrocentosquatro.storemanagement.controller.GerenciarUsuarios;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author Everton Ferreira
 */
public class TelaAdicionarAdm extends JFrame {

    private JTextField campoNome;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton buttonAdicionar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    public TelaAdicionarAdm() {
        setTitle("Adicionar Administrador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campo Nome
        campoNome = new JTextField("Nome");
        campoNome.setBounds(30, 30, 160, 30);
        campoNome.addMouseListener(getPlaceholderClearListener(campoNome, "Nome"));
        add(campoNome);

        // Campo Login
        campoLogin = new JTextField("Login");
        campoLogin.setBounds(210, 30, 160, 30);
        campoLogin.addMouseListener(getPlaceholderClearListener(campoLogin, "Login"));
        add(campoLogin);

        // Campo Senha
        campoSenha = new JPasswordField("Senha");
        campoSenha.setBounds(390, 30, 160, 30);
        campoSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String senhaTexto = new String(campoSenha.getPassword());
                if (senhaTexto.equals("Senha")) {
                    campoSenha.setText("");
                }
            }
        });
        add(campoSenha);

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);
        buttonVoltar.addActionListener(e -> {
              System.out.println("Volta para tela usuario");
            new TelaUsuario().setVisible(true);
            dispose();
        });

        // Botão Home
        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);
        buttonHome.addActionListener(e -> {
            System.out.println("Volta para tela home");
            new TelaHome().setVisible(true);
            dispose();
        });

        // Botão Adicionar
        buttonAdicionar = new JButton("Adicionar");
        buttonAdicionar.setBounds(390, 300, 120, 30);
        add(buttonAdicionar);
        buttonAdicionar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            String login = campoLogin.getText().trim();
            String senha = new String(campoSenha.getPassword()).trim();

            if (nome.isEmpty() || nome.equals("Nome") ||
                login.isEmpty() || login.equals("Login") ||
                senha.isEmpty() || senha.equals("Senha")) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            GerenciarUsuarios gerenciador = new GerenciarUsuarios();
            gerenciador.adicionarAdministrador(nome, login, senha);
            JOptionPane.showMessageDialog(this, "Administrador adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new TelaUsuario().setVisible(true); 
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
        SwingUtilities.invokeLater(() -> new TelaAdicionarAdm().setVisible(true));
    }
}
