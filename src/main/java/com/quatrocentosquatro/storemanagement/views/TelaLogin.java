package com.quatrocentosquatro.storemanagement.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.quatrocentosquatro.storemanagement.controller.LoginController;

/**
 * @author Everton Ferreira
 */
public class TelaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton buttonLogin;

    private LoginController loginController;

    public TelaLogin() {
        super("Tela de login");

        loginController = new LoginController();

        //configuração da janela
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Usuário
        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(50, 30, 80, 25);
        add(labelUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(130, 30, 200, 25);
        add(campoUsuario);

        // Senha
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 70, 80, 25);
        add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(130, 70, 200, 25);
        add(campoSenha);

        // Botão
        buttonLogin = new JButton("Entrar");
        buttonLogin.setBounds(130, 110, 100, 30);
        add(buttonLogin);

        // Ação do botão
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (loginController.autenticar(usuario, senha)) {
                     System.out.print("Login feito com sucesso");
                     new TelaHome().setVisible(true);
                     dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                }
            }
        });
    }

    // esse metado tem que ficar aqui para os teste e para iniciar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}