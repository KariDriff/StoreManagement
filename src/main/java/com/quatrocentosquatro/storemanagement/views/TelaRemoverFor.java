package com.quatrocentosquatro.storemanagement.views;

import com.quatrocentosquatro.storemanagement.controller.GerenciarFornecedores;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author Everton Ferreira
 */
public class TelaRemoverFor extends JFrame {
    private JTextField campoId;
    private JButton buttonRemover;
    private JButton buttonVoltar;
    private JButton buttonHome;

    private GerenciarFornecedores gerenciador;

    public TelaRemoverFor() {
        setTitle("Remover Fornecedor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        gerenciador = new GerenciarFornecedores();

        // Campo ID
        campoId = new JTextField("Id");
        campoId.setBounds(30, 30, 160, 30);
        campoId.addMouseListener(getPlaceholderClearListener(campoId, "Id"));
        add(campoId);

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);
        buttonVoltar.addActionListener(e -> {
            System.out.println("Abrir tela de Fornecedor");
            new TelaFornecedor().setVisible(true);
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

        // Botão Remover
        buttonRemover = new JButton("Remover");
        buttonRemover.setBounds(390, 300, 120, 30);
        add(buttonRemover);
        buttonRemover.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText());

                if (gerenciador.buscarPorId(id) != null) {
                    gerenciador.removerFornecedor(id);
                    JOptionPane.showMessageDialog(this, "Fornecedor removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Fornecedor não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido");
            }
        });
    }

    // Limpa texto padrão ao clicar
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
        SwingUtilities.invokeLater(() -> new TelaRemoverFor().setVisible(true));
    }
}
