package com.quatrocentosquatro.storemanagement.views;

//importações de arquivos:
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TelaFornecedor extends JFrame{
    private JButton buttonAdicionarFornecedores;
    private JButton buttonListasdFornecedores;
    private JButton buttonBuscarRapidaFornecedores;
    private JButton buttonAtualizarFornecedores;
    private JButton buttonRemoverFornecedores;
    private JButton buttonVoltarTelaHome;
     
     public TelaFornecedor(){
         // Configuração da tela
        setTitle("Tela Fornecedores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // botão adicionar fornecedores (irá direcionara para a pagina para fazer essa ação)
        buttonAdicionarFornecedores = new JButton("Adicionar Fornecedores");
        buttonAdicionarFornecedores.setBounds(50, 50, 150, 30);
        add(buttonAdicionarFornecedores);

        // botão listas de fornecedores (irá direcionara para a pagina para fazer essa ação)
        buttonListasdFornecedores = new JButton("Listas de Fornecedores");
        buttonListasdFornecedores.setBounds(230, 50, 150, 30);
        add( buttonListasdFornecedores);
 
        // botão Buscar rapida por id (irá direcionara para a pagina para fazer essa ação)
        buttonBuscarRapidaFornecedores = new JButton("Busca rapida");
        buttonBuscarRapidaFornecedores.setBounds(50, 100, 150, 30);
        add(buttonBuscarRapidaFornecedores);

         // botão remover fornecedor (irá direcionara para a pagina para fazer essa ação)
        buttonRemoverFornecedores = new JButton("Remove Fornecedores");
        buttonRemoverFornecedores.setBounds(230, 100, 150, 30);
        add(buttonRemoverFornecedores);

         // botão remover fornecedor (irá direcionara para a pagina para fazer essa ação)
        buttonAtualizarFornecedores = new JButton("Atualizar Fornecedores");
        buttonAtualizarFornecedores.setBounds(410, 50, 150, 30);
        add(buttonAtualizarFornecedores);

        //voltar para tela incial
        buttonVoltarTelaHome = new JButton("Home");
        buttonVoltarTelaHome.setBounds(50, 300, 100, 30);
        add(buttonVoltarTelaHome);

        //fEITO MAS SEM CONEXÃO
        // Ações
        buttonAdicionarFornecedores.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de adicionar fornecedor");
              new TelaAdicionarFornecedor().setVisible(true);
              // essa função vai fechar a pagina automaticamente
              dispose();
            }
        });
  
        buttonListasdFornecedores.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de lista fornecedor");
            // importação aqui exemplo abaixo
            }
        });
        //feito
        buttonBuscarRapidaFornecedores.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de busca");
              new TelaBuscarRapidaFor().setVisible(true);
              dispose();
            }
        });
          
        buttonRemoverFornecedores.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de remover fornecedor");
              new TelaRemoverFor().setVisible(true);
              dispose();
            }
        });

        buttonAtualizarFornecedores.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             System.out.println("Abrir tela de atualizar fornecedor");
             new TelaAtualizarFor().setVisible(true);
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
            new TelaFornecedor().setVisible(true);
        });
    }
}
