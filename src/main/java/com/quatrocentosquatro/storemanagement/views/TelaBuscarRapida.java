package com.quatrocentosquatro.storemanagement.views;

import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;
import com.quatrocentosquatro.storemanagement.model.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaBuscarRapida extends JFrame {
    private JTextField campoBuscaRapida;
    private JButton buttonBusca;
    private JButton buttonVoltar;
    private JButton buttonHome;
    private GerenciarEstoque gerenciador; // Instância do controlador

    public TelaBuscarRapida() {
        setTitle("Busca Produto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // CORREÇÃO: usar o padrão Singleton
        gerenciador = GerenciarEstoque.getInstancia(); 

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

        buttonHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir tela de Home");
                new TelaHome().setVisible(true);
                dispose();
            }
        });

        buttonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir tela de Estoque");
                new TelaEstoque().setVisible(true);
                dispose();
            }
        });

        buttonBusca = new JButton("Busca");
        buttonBusca.setBounds(390, 300, 100, 30);
        add(buttonBusca);

        // Lógica de busca
        buttonBusca.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoBuscaRapida.getText());
                Produto produto = gerenciador.buscarPorId(id);

                if (produto != null) {
                    JOptionPane.showMessageDialog(this,
                            "Produto encontrado:\n" +
                                    "ID: " + produto.getId() + "\n" +
                                    "Nome: " + produto.getNome() + "\n" +
                                    "Marca: " + produto.getMarca() + "\n" +
                                    "Preço: R$ " + produto.getPreco());
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.");
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
            new TelaBuscarRapida().setVisible(true);
        });
    }
}
