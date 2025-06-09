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

import com.quatrocentosquatro.storemanagement.controller.GerenciarUsuarios;
import com.quatrocentosquatro.storemanagement.model.Usuario;

/**
 * @author Everton Ferreira
 */
public class TelaListaUser extends JFrame {
    private JTable tabelaUsuarios;
    private JButton buttonVoltar;
    private JButton buttonHome;

    private GerenciarUsuarios gerenciador;

    public TelaListaUser() {
        setTitle("Lista de Usuários");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gerenciador = new GerenciarUsuarios();
        setLayout(new BorderLayout());
        String[] colunas = {"ID", "Nome"};

        // Modelo da tabela
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        // Buscar lista de usuários
        List<Usuario> usuarios = gerenciador.listarUsuarios();

        // Preencher modelo com dados dos usuários:
        for (Usuario u : usuarios) {
            Object[] linha = {
                u.getId(),
                u.getNome()
            };
            modelo.addRow(linha);
        }

        tabelaUsuarios = new JTable(modelo);
        // para não permitir edição
        tabelaUsuarios.setEnabled(false);  

        // Adiciona tabela em um scroll pane
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões na parte inferior
        JPanel painelBotoes = new JPanel();
        buttonVoltar = new JButton("Voltar");
        buttonHome = new JButton("Home");
        painelBotoes.add(buttonVoltar);
        painelBotoes.add(buttonHome);

        add(painelBotoes, BorderLayout.SOUTH);

        buttonVoltar.addActionListener(e -> {
            System.out.println("Abrir tela de adicionar Usuario");
            new TelaUsuario().setVisible(true);  
            dispose();
        });

        buttonHome.addActionListener(e -> {
            System.out.println("Abrir tela de Home");
            new TelaHome().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaListaUser().setVisible(true));
    }
}
