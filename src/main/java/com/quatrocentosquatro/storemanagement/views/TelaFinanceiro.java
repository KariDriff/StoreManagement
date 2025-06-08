package com.quatrocentosquatro.storemanagement.views;

// importação da bibliotecas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TelaFinanceiro extends JFrame{
    private JButton buttonRegistrarEntradaUser;
    private JButton buttonRegistrarSaidaUser;
    private JButton buttonComprarProdutos;
    private JButton buttonPagarDespesa;
    private JButton buttonGerarRelatorio;
    private JButton buttonVoltarTelaHome;
    private JButton buttonVoltar;

    public TelaFinanceiro(){
        // Configuração da tela
        setTitle("Tela Financeiro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
   
        buttonRegistrarEntradaUser = new JButton("Registrar EntUser");
        buttonRegistrarEntradaUser.setBounds(50, 50, 150, 30);
        add(buttonRegistrarEntradaUser);

        buttonRegistrarSaidaUser = new JButton("Registrar SaiUser");
        buttonRegistrarSaidaUser.setBounds(230, 50, 150, 30);;
        add(buttonRegistrarSaidaUser);

        buttonComprarProdutos = new JButton("Comprar Produtos");
        buttonComprarProdutos.setBounds(50, 100, 150, 30);
        add(buttonComprarProdutos);

        buttonPagarDespesa = new JButton("Pagar Despesa");
        buttonPagarDespesa.setBounds(230, 100, 150, 30);
        add(buttonPagarDespesa);

        buttonGerarRelatorio = new JButton("Gerar Relatorio");
        buttonGerarRelatorio.setBounds(410, 50, 150, 30);
        add(buttonGerarRelatorio);


        buttonVoltarTelaHome = new JButton("Home");
        buttonVoltarTelaHome.setBounds(150, 300, 100, 30);
        add(buttonVoltarTelaHome);

         buttonGerarRelatorio.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("voltar para entrada user");
              new TelaRegistrarEntradaUser().setVisible(true);
              dispose();
            }
        });

        buttonRegistrarEntradaUser.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("voltar para entrada user");
              new TelaRegistrarEntradaUser().setVisible(true);
              dispose();
            }
        });

        buttonPagarDespesa.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              new TelaManuntenco().setVisible(true);
              dispose();
            }
        });

        buttonComprarProdutos.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              new TelaManuntenco().setVisible(true);
              dispose();
            }
        });

        buttonComprarProdutos.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              new TelaManuntenco().setVisible(true);
              dispose();
            }
        });

           buttonVoltarTelaHome.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("voltar para home");
              new TelaHome().setVisible(true);
              dispose();
            }
        });

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);
        buttonVoltar.addActionListener(e -> {
            System.out.println("Abrir tela de Fornecedor");
            new TelaFornecedor().setVisible(true);
            dispose();
            
        });
    }

      public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaFinanceiro().setVisible(true);
        });
    }
}
