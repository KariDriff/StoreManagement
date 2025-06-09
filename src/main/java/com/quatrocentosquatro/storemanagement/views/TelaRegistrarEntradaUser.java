package com.quatrocentosquatro.storemanagement.views;

import com.quatrocentosquatro.storemanagement.controller.Financeiro;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author Everton Ferreira
 */
public class TelaRegistrarEntradaUser extends JFrame {
    private JTextField campoValor;
    private JTextField campoDescricao;
    private JButton buttonRegistrar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    public TelaRegistrarEntradaUser() {
        setTitle("Registrar Entrada");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        campoValor = new JTextField("Valor");
        campoValor.setBounds(30, 30, 160, 30);
        campoValor.addMouseListener(getPlaceholderClearListener(campoValor, "Valor"));
        add(campoValor);

        campoDescricao = new JTextField("Descrição");
        campoDescricao.setBounds(30, 80, 320, 30);
        campoDescricao.addMouseListener(getPlaceholderClearListener(campoDescricao, "Descrição"));
        add(campoDescricao);

        // Botão Registrar
        buttonRegistrar = new JButton("Registrar");
        buttonRegistrar.setBounds(30, 130, 120, 30);
        add(buttonRegistrar);

        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float valor = Float.parseFloat(campoValor.getText());
                    String descricao = campoDescricao.getText();

                    Financeiro financeiro = new Financeiro();
                    financeiro.registrarEntradaUser(valor, descricao);

                    JOptionPane.showMessageDialog(null, "Entrada registrada com sucesso!");

                    campoValor.setText("Valor");
                    campoDescricao.setText("Descrição");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido! Digite um número correto.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar entrada: " + ex.getMessage());
                }
            }
        });

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(160, 180, 100, 25);
        add(buttonVoltar);

        buttonVoltar.addActionListener(e -> {
            new TelaFinanceiro().setVisible(true);
            dispose();
        });

        // Botão Home
        buttonHome = new JButton("Home");
        buttonHome.setBounds(270, 180, 80, 25);
        add(buttonHome);

        buttonHome.addActionListener(e -> {
            new TelaHome().setVisible(true);
            dispose();
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
        SwingUtilities.invokeLater(() -> new TelaRegistrarEntradaUser().setVisible(true));
    }
}
