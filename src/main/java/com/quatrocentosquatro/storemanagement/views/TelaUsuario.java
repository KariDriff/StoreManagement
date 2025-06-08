package com.quatrocentosquatro.storemanagement.views;

//importações de arquivos:
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TelaUsuario extends JFrame{
    private JButton buttonAdcionarFuncionario;
    private JButton buttonAdicionarAdm;
    private JButton buttonListaUsuarios;
    private JButton butttonporid;
    private JButton buttonAtualizar;
    private JButton buttonRomeveUsuario;
    private JButton buttonVoltarTelaHome;

     public TelaUsuario(){
        // Configuração da tela
        setTitle("Tela Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        buttonAdcionarFuncionario = new JButton("Adicionar Funcionario");
        buttonAdcionarFuncionario.setBounds(50, 50, 150, 30);
        add(buttonAdcionarFuncionario);

        buttonAdicionarAdm = new JButton("Adicionar ADM");
        buttonAdicionarAdm.setBounds(230, 50, 150, 30);
        add(buttonAdicionarAdm);

        buttonListaUsuarios = new JButton("Lista de Usuarios");
        buttonListaUsuarios.setBounds(50, 100, 150, 30);
        add(buttonListaUsuarios);

        butttonporid = new JButton("Busca Rapida");
        butttonporid.setBounds(230, 100, 150, 30);
        add(butttonporid);

        buttonAtualizar = new JButton("Atualizar dados");
        buttonAtualizar.setBounds(410, 50, 150, 30);
        add(buttonAtualizar);

        buttonRomeveUsuario = new JButton("Remover Usuario");
        buttonRomeveUsuario.setBounds(410, 100, 150, 30);
        add(buttonRomeveUsuario);

        buttonVoltarTelaHome = new JButton("Home");
        buttonVoltarTelaHome.setBounds(50, 300, 100, 30);
        add(buttonVoltarTelaHome);

         buttonAdcionarFuncionario.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de adicionar Funcionario");
             new TelaAdicionarUser().setVisible(true);
             dispose();
            }
        });
   
         buttonAdicionarAdm.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de adicionar ADM");
             new TelaAdicionarAdm().setVisible(true);
             dispose();
            }
        });

        buttonListaUsuarios.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de lISTA DE USUARIOS");
             new TelaListaUser().setVisible(true);
              dispose();
            }
        });

        butttonporid.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de busca rapida");
             new TelaBuscaRapidaUser().setVisible(true);
              dispose();
            }
        });

        buttonAtualizar.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de atualizar dados");
             new TelaAtualizarUser().setVisible(true);
              dispose();
            }
        });

         buttonRomeveUsuario.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de Remover Usuario");
              new TelaRemoverUser().setVisible(true);
              dispose();
            }
        });

        buttonVoltarTelaHome.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.out.println("Abrir tela de Home");
            new TelaHome().setVisible(true);
            dispose();
            }
        });
     }
      public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaUsuario().setVisible(true);
        });
    }
}
