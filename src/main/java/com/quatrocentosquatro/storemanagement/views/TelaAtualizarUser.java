package com.quatrocentosquatro.storemanagement.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.quatrocentosquatro.storemanagement.controller.GerenciarUsuarios;
import com.quatrocentosquatro.storemanagement.model.Usuario;

/**
 * @author Everton Ferreira
 */
public class TelaAtualizarUser extends JFrame {
    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoLogin;
    private JTextField campoSenha;
    private JButton buttonAtualizar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    private GerenciarUsuarios gerenciador;

    public TelaAtualizarUser() {
        setTitle("Atualizar Usuário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        gerenciador = new GerenciarUsuarios();

        // Campo ID
        campoId = new JTextField("Id");
        campoId.setBounds(30, 30, 160, 30);
        campoId.addMouseListener(getPlaceholderClearListener(campoId, "Id"));
        add(campoId);

        // Campo Nome
        campoNome = new JTextField("Nome");
        campoNome.setBounds(210, 30, 160, 30);
        campoNome.addMouseListener(getPlaceholderClearListener(campoNome, "Nome"));
        add(campoNome);

        // Campo Login
        campoLogin = new JTextField("Login");
        campoLogin.setBounds(390, 30, 160, 30);
        campoLogin.addMouseListener(getPlaceholderClearListener(campoLogin, "Login"));
        add(campoLogin);

        // Campo Senha
        campoSenha = new JTextField("Senha");
        campoSenha.setBounds(30, 80, 160, 30);
        campoSenha.addMouseListener(getPlaceholderClearListener(campoSenha, "Senha"));
        add(campoSenha);

        // Botão Atualizar
        buttonAtualizar = new JButton("Atualizar");
        buttonAtualizar.setBounds(390, 300, 120, 30);
        add(buttonAtualizar);

        buttonAtualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText().trim());
                Usuario usuarioExistente = gerenciador.buscarPorId(id);

                if (usuarioExistente != null) {
                    gerenciador.atualizarUsuario(
                        id,
                        campoNome.getText().trim(),
                        campoLogin.getText().trim(),
                        campoSenha.getText().trim()
                    );
                    JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);
        buttonVoltar.addActionListener(e -> {
            System.out.println("Abrir tela de Usuario");
            new TelaUsuario().setVisible(true);
            dispose();
        });

        // Botão Home
        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);
        buttonHome.addActionListener(e -> {
            System.out.println("Abrir tela de Home");
            new TelaHome().setVisible(true);
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
        SwingUtilities.invokeLater(() -> new TelaAtualizarUser().setVisible(true));
    }
}
