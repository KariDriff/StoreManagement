package com.quatrocentosquatro.storemanagement.views;

import com.quatrocentosquatro.storemanagement.controller.GerenciarFornecedores;
import com.quatrocentosquatro.storemanagement.model.Fornecedor;

import javax.swing.*;
import java.awt.event.*;

public class TelaAtualizarFor extends JFrame {
    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextField campoCnpj;
    private JButton buttonAtualizar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    private GerenciarFornecedores gerenciador;

    public TelaAtualizarFor() {
        setTitle("Atualizar Fornecedor");
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

        campoNome = new JTextField("Nome");
        campoNome.setBounds(150, 30, 160, 30);
        campoNome.addMouseListener(getPlaceholderClearListener(campoNome, "Nome"));
        add(campoNome);

        campoTelefone = new JTextField("Telefone");
        campoTelefone.setBounds(330, 30, 160, 30);
        campoTelefone.addMouseListener(getPlaceholderClearListener(campoTelefone, "Telefone"));
        add(campoTelefone);

        campoEmail = new JTextField("Email");
        campoEmail.setBounds(30, 80, 160, 30);
        campoEmail.addMouseListener(getPlaceholderClearListener(campoEmail, "Email"));
        add(campoEmail);

        campoCnpj = new JTextField("CNPJ");
        campoCnpj.setBounds(210, 80, 160, 30);
        campoCnpj.addMouseListener(getPlaceholderClearListener(campoCnpj, "CNPJ"));
        add(campoCnpj);

        buttonAtualizar = new JButton("Atualizar");
        buttonAtualizar.setBounds(390, 300, 120, 30);
        add(buttonAtualizar);

        buttonAtualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText());
                Fornecedor fornecedorExistente = gerenciador.buscarPorId(id);

                if (fornecedorExistente != null) {
                    gerenciador.atualizarFornecedor(
                        id,
                        campoNome.getText(),
                        campoTelefone.getText(),
                        campoEmail.getText(),
                        campoCnpj.getText()
                    );
                    JOptionPane.showMessageDialog(this, "Fornecedor atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Fornecedor não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);
        buttonVoltar.addActionListener(e -> {
            new TelaFornecedor().setVisible(true);
            dispose();
        });

        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);
        buttonHome.addActionListener(e -> {
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
        SwingUtilities.invokeLater(() -> new TelaAtualizarFor().setVisible(true));
    }
}
