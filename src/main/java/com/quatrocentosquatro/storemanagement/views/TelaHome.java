package com.quatrocentosquatro.storemanagement.views;

//importação dos arquivos:


// importação da bibliotecas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.quatrocentosquatro.storemanagement.controller.Financeiro;
import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;

public class TelaHome extends JFrame {
    private JButton buttonEstoque;
    private JButton buttonFuncionarios;
    private JButton buttonFinanceiro;
    private JButton buttonFornecedores;
    private JButton buttonPVD;


    public TelaHome() {
        // Configuração da tela
        setTitle("Tela Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Botão Estoque
        buttonEstoque = new JButton("Estoque");
        buttonEstoque.setBounds(50, 50, 150, 30);
        add(buttonEstoque);

        // Botão Funcionários
        buttonFuncionarios = new JButton("Funcionários");
        buttonFuncionarios.setBounds(230, 50, 150, 30);
        add(buttonFuncionarios);

        // Botão Financeiro
        buttonFinanceiro = new JButton("Financeiro");
        buttonFinanceiro.setBounds(50, 100, 150, 30);
        add(buttonFinanceiro);

          // Botão Financeiro
        buttonFornecedores = new JButton("Fornecedores");
        buttonFornecedores.setBounds(230, 100, 150, 30);
        add(buttonFornecedores);

        buttonPVD = new JButton("PVD");
        buttonPVD.setBounds(410, 50, 150, 30);
        add(buttonPVD);

        // Ações dos botões
        buttonEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             new TelaEstoque().setVisible(true);
               // essa função vai fechar a pagina automaticamente
               dispose(); 
            }
        });

        buttonFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir tela de Funcionários");
                new TelaUsuario().setVisible(true);
                 dispose();
            }
        });

        buttonFornecedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir tela de Fornecedores");
                new TelaFornecedor().setVisible(true);
                dispose();
            }
        });

        buttonPVD.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
          System.out.println("Abrir tela de PVD");
          GerenciarEstoque estoqueController = new GerenciarEstoque();
          Financeiro financeiroController = new Financeiro();

          new TelaPDV(estoqueController, financeiroController).setVisible(true);
          dispose();
          }
        });


            buttonFinanceiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir tela de Financeiros");
                new TelaFinanceiro().setVisible(true);
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaHome().setVisible(true);
        });
    }
}