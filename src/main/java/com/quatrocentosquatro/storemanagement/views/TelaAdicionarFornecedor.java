package com.quatrocentosquatro.storemanagement.views;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaAdicionarFornecedor extends JFrame {
    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextField campoCnpj;
    private JButton buttonAdicionar;
    private JButton buttonVoltar;
    private JButton buttonHome;


    public TelaAdicionarFornecedor() {
        setTitle("Adicionar Fornecedor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

         // Campo Produto
        campoNome = new JTextField("Nome");
        campoNome.setBounds(30, 30, 160, 30);
        campoNome.addMouseListener(getPlaceholderClearListener(campoNome, "Nome"));
        add(campoNome);

        // Campo Marca
        campoTelefone = new JTextField("Telefone");
        campoTelefone.setBounds(210, 30, 160, 30);
        campoTelefone.addMouseListener(getPlaceholderClearListener(campoTelefone, "Telefone"));
        add(campoTelefone);

        // Campo ID (informativo)
        campoEmail = new JTextField("Email");
        campoEmail.setBounds(390, 30, 160, 30);
        campoEmail.addMouseListener(getPlaceholderClearListener(campoEmail, "Email"));
        add(campoEmail);

        // Campo Quantidade
        campoCnpj = new JTextField("Quantidade");
        campoCnpj.setBounds(30, 80, 160, 30);
        campoCnpj.addMouseListener(getPlaceholderClearListener(campoCnpj, "Quantidade"));
        add(campoCnpj);

         // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);

        buttonVoltar.addActionListener((ActionEvent e) -> {
            System.out.println("Abrir tela de Fornecedor");
            new TelaFornecedor().setVisible(true);
            dispose();
        });

        // Botão Home
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

         // Botão Cadastrar
        buttonAdicionar = new JButton("Adicionar");
        buttonAdicionar.setBounds(390, 300, 120, 30);
        add(buttonAdicionar);
    }

      // Limpa texto padrão ao clicar
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
        SwingUtilities.invokeLater(() -> new TelaAdicionarFornecedor().setVisible(true));
    }
}

