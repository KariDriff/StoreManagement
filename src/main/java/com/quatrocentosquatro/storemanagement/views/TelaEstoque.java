package com.quatrocentosquatro.storemanagement.views;

// importação da bibliotecas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TelaEstoque extends JFrame{
    private JButton buttonAdicionarProduto;
    private JButton buttonAtualizarProduto;
    private JButton buttonBuscarProduto;
    private JButton buttonListaProdutos;
    private JButton buttonVoltarTelaHome;

    public TelaEstoque(){
      // Configuração da tela
        setTitle("Tela Estoque");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        buttonAdicionarProduto = new JButton("Adicionar Produto");
        buttonAdicionarProduto.setBounds(50, 50, 150, 30);
        add(buttonAdicionarProduto);
        
        buttonAtualizarProduto = new JButton("Atualizar Produto");
        buttonAtualizarProduto.setBounds(230, 50, 150, 30);
        add(buttonAtualizarProduto);

        buttonBuscarProduto = new JButton("Buscar Produto");
        buttonBuscarProduto.setBounds(50, 100, 150, 30);
        add(buttonBuscarProduto);

        buttonListaProdutos = new JButton("Lista de Produto");
        buttonListaProdutos.setBounds(230, 100, 150, 30);
        add(buttonListaProdutos);

         //voltar para tela incial
        buttonVoltarTelaHome = new JButton("Home");
        buttonVoltarTelaHome.setBounds(50, 300, 100, 30);
        add(buttonVoltarTelaHome);

        buttonAdicionarProduto.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de adicionar Produto");
              new TelaAdicionarProduto().setVisible(true);
              dispose();
            }
        });

        buttonAtualizarProduto.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de Atualizar Produto");
            // importação aqui exemplo abaixo
             new TelaAtualizarProduto().setVisible(true);
             dispose();
            }
        });

        buttonBuscarProduto.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de buscar Produto");
              new TelaBuscarRapida().setVisible(true);
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
    }
      public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaEstoque().setVisible(true);
        });
    }
}
