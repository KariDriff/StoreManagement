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

public class TelaBuscaRapidaUser extends JFrame {

    private JTextField campoBuscaRapida;
    private JButton buttonBusca;
    private JButton buttonVoltar;
    private JButton buttonHome;
    private GerenciarUsuarios gerenciador;

    public TelaBuscaRapidaUser() {
        setTitle("Busca Rápida Usuário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        gerenciador = new GerenciarUsuarios();

        // Campo ID para busca rápida
        campoBuscaRapida = new JTextField("Id");
        campoBuscaRapida.setBounds(30, 30, 160, 30);
        campoBuscaRapida.addMouseListener(getPlaceholderClearListener(campoBuscaRapida, "Id"));
        add(campoBuscaRapida);

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

        // Botão Busca
        buttonBusca = new JButton("Busca");
        buttonBusca.setBounds(390, 300, 100, 30);
        add(buttonBusca);
        buttonBusca.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoBuscaRapida.getText().trim());
                Usuario usuario = gerenciador.buscarPorId(id);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(this,
                        "Usuário encontrado:\n" +
                        "ID: " + usuario.getId() + "\n" +
                        "Nome: " + usuario.getNome() + "\n" +
                        "Login: " + usuario.getLogin() + "\n" +
                        "Tipo: " + usuario.getClass().getSimpleName()
                    );
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido.");
            }
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
        SwingUtilities.invokeLater(() -> {
            new TelaBuscaRapidaUser().setVisible(true);
        });
    }
}
