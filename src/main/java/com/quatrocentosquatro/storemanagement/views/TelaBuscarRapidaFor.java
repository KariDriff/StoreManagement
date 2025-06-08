package com.quatrocentosquatro.storemanagement.views;

import com.quatrocentosquatro.storemanagement.controller.GerenciarFornecedores;
import com.quatrocentosquatro.storemanagement.model.Fornecedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaBuscarRapidaFor extends JFrame {
    private JTextField campoBuscaRapida;
    private JButton buttonBusca;
    private JButton buttonVoltar;
    private JButton buttonHome;
    private GerenciarFornecedores gerenciador; 
    public TelaBuscarRapidaFor() {
        setTitle("Busca Fornecedor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        gerenciador = new GerenciarFornecedores();

        campoBuscaRapida = new JTextField("Id");
        campoBuscaRapida.setBounds(30, 30, 160, 30);
        campoBuscaRapida.addMouseListener(getPlaceholderClearListener(campoBuscaRapida, "Id"));
        add(campoBuscaRapida);

        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);

        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);

        buttonHome.addActionListener(e -> {
            System.out.println("Abrir tela de Home");
            new TelaHome().setVisible(true);
            dispose();
        });

        buttonVoltar.addActionListener(e -> {
            System.out.println("Abrir tela de Fornecedor");
            new TelaFornecedor().setVisible(true);
            dispose();
        });

        buttonBusca = new JButton("Busca");
        buttonBusca.setBounds(390, 300, 100, 30);
        add(buttonBusca);

        buttonBusca.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoBuscaRapida.getText());
                Fornecedor fornecedor = (Fornecedor) gerenciador.buscarPorId(id);

                if (fornecedor != null) {
                    JOptionPane.showMessageDialog(this,
                            "Fornecedor encontrado:\n" +
                            "ID: " + fornecedor.getId() + "\n" +
                            "Nome: " + fornecedor.getNome() + "\n" +
                            "Telefone: " + fornecedor.getTelefone() + "\n" +
                            "Email: " + fornecedor.getEmail() + "\n" +
                            "CNPJ: " + fornecedor.getCnpj());
                } else {
                    JOptionPane.showMessageDialog(this, "Fornecedor não encontrado.");
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
            new TelaBuscarRapidaFor().setVisible(true); 
        });
    }
}
