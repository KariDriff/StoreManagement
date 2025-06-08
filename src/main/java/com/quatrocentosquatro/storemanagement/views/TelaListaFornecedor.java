package com.quatrocentosquatro.storemanagement.views;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.quatrocentosquatro.storemanagement.controller.GerenciarFornecedores;
import com.quatrocentosquatro.storemanagement.model.Fornecedor;

public class TelaListaFornecedor extends JFrame {
    private JTable tabelaFornecedores;
    private JButton buttonVoltar;
    private JButton buttonHome;

    private GerenciarFornecedores gerenciador;

    public TelaListaFornecedor() {
        setTitle("Lista de Fornecedores");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gerenciador = new GerenciarFornecedores();
        setLayout(new BorderLayout());

        // Colunas da tabela
        String[] colunas = {"ID", "Nome", "CNPJ", "Telefone"};

        // Modelo da tabela
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        // Buscar lista de fornecedores
        List<Fornecedor> fornecedores = gerenciador.listarFornecedores();

        // Preencher modelo com dados dos fornecedores:
        for (Fornecedor f : fornecedores) {
            Object[] linha = {
                f.getId(),
                f.getNome(),
                f.getCnpj(),
                f.getTelefone()
            };
            modelo.addRow(linha);
        }

        tabelaFornecedores = new JTable(modelo);
        tabelaFornecedores.setEnabled(false);  // Desabilita edição

        // Adiciona tabela em um scroll pane
        JScrollPane scrollPane = new JScrollPane(tabelaFornecedores);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões na parte inferior
        JPanel painelBotoes = new JPanel();
        buttonVoltar = new JButton("Voltar");
        buttonHome = new JButton("Home");
        painelBotoes.add(buttonVoltar);
        painelBotoes.add(buttonHome);

        add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        buttonVoltar.addActionListener(e -> {
            System.out.println("Abrir tela de adicionar Fornecedor");
            new TelaFornecedor().setVisible(true);  // Supondo que essa tela exista para adicionar/editar fornecedores
            dispose();
        });

        buttonHome.addActionListener(e -> {
            System.out.println("Abrir tela de Home");
            new TelaHome().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaListaFornecedor().setVisible(true));
    }
}

