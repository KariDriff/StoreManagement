package com.quatrocentosquatro.storemanagement.views;

// importação da bibliotecas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TelaFinanceiro extends JFrame{
    private JButton buttonRegistrarEntrada;
    private JButton buttonRegistrarEntradaUser;
    private JButton buttonRegistrarSaida;
    private JButton buttonRegistrarSaidaUser;
    private JButton buttonComprarProdutos;
    private JButton buttonPagarDespesa;
    private JButton buttonGerarRelatorio;
    private JButton buttonRegistrarOperacoes;
    private JButton buttonVoltarTelaHome;

    public TelaFinanceiro(){
        // Configuração da tela
        setTitle("Tela Financeiro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        buttonRegistrarEntrada = new JButton("Registrar Entrada");
        buttonRegistrarEntrada.setBounds(50, 50, 150, 30);
        add(buttonRegistrarEntrada);
        
        buttonRegistrarEntradaUser = new JButton("Registrar EntUser");
        buttonRegistrarEntradaUser.setBounds(230, 50, 150, 30);
        add(buttonRegistrarEntradaUser);

        buttonRegistrarSaida= new JButton("Registrar Saida");
        buttonRegistrarSaida.setBounds(50, 100, 150, 30);
        add(buttonRegistrarSaida);

        buttonRegistrarSaidaUser = new JButton("Registrar SaiUser");
        buttonRegistrarSaidaUser.setBounds(230, 100, 150, 30);
        add(buttonRegistrarSaidaUser);

        buttonComprarProdutos = new JButton("Comprar Produtos");
        buttonComprarProdutos.setBounds(410, 50, 150, 30);
        add(buttonComprarProdutos);

        buttonPagarDespesa = new JButton("Pagar Despesa");
        buttonPagarDespesa.setBounds(410, 100, 150, 30);
        add(buttonPagarDespesa);

        buttonGerarRelatorio = new JButton("Gerar Relatorio");
        buttonGerarRelatorio.setBounds(50, 150, 150, 30);
        add(buttonGerarRelatorio);

        buttonRegistrarOperacoes = new JButton("Registrar Operacoes");
        buttonRegistrarOperacoes.setBounds(230, 150, 150, 30);
        add(buttonRegistrarOperacoes);

        buttonVoltarTelaHome = new JButton("Home");
        buttonVoltarTelaHome.setBounds(50, 300, 100, 30);
        add(buttonVoltarTelaHome);

         buttonVoltarTelaHome.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("voltar para home");
              new TelaHome().setVisible(true);
              dispose();
            }
        });
    }

      public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaFinanceiro().setVisible(true);
        });
    }
}
