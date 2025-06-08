package com.quatrocentosquatro.storemanagement.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.quatrocentosquatro.storemanagement.controller.GerenciarUsuarios;

public class TelaAdicionarUser extends JFrame {

    private JTextField campoNome;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JTextField campoCargo;
    private JButton buttonAdicionar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    public TelaAdicionarUser() {
        setTitle("Adicionar Usuário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campo Nome
        campoNome = new JTextField("Nome");
        campoNome.setBounds(30, 30, 140, 30);
        campoNome.addMouseListener(getPlaceholderClearListener(campoNome, "Nome"));
        add(campoNome);

        // Campo Login
        campoLogin = new JTextField("Login");
        campoLogin.setBounds(190, 30, 140, 30);
        campoLogin.addMouseListener(getPlaceholderClearListener(campoLogin, "Login"));
        add(campoLogin);

        // Campo Senha
        campoSenha = new JPasswordField("Senha");
        campoSenha.setBounds(350, 30, 140, 30);
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

        // Campo Cargo
        campoCargo = new JTextField("Cargo");
        campoCargo.setBounds(30, 80, 140, 30);
        campoCargo.addMouseListener(getPlaceholderClearListener(campoCargo, "Cargo"));
        add(campoCargo);

        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);
        buttonVoltar.addActionListener(e -> {
            System.out.println("Abrir tela de Usuario");
            new TelaUsuario().setVisible(true);
            dispose();
        });

        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);
        buttonHome.addActionListener(e -> {
            System.out.println("Abrir tela de Home");
            new TelaHome().setVisible(true);
            dispose();
        });

        buttonAdicionar = new JButton("Adicionar");
        buttonAdicionar.setBounds(390, 300, 120, 30);
        add(buttonAdicionar);
        buttonAdicionar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            String login = campoLogin.getText().trim();
            String senha = new String(campoSenha.getPassword()).trim();
            String cargo = campoCargo.getText().trim();

            if (nome.isEmpty() || nome.equals("Nome") ||
                login.isEmpty() || login.equals("Login") ||
                senha.isEmpty() || senha.equals("Senha") ||
                cargo.isEmpty() || cargo.equals("Cargo")) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            GerenciarUsuarios gerenciador = new GerenciarUsuarios();
            gerenciador.adicionarFuncionario(nome, login, senha, cargo);
            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
        SwingUtilities.invokeLater(() -> new TelaAdicionarUser().setVisible(true));
    }
}

