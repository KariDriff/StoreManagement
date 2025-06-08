package com.quatrocentosquatro.storemanagement.views;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.quatrocentosquatro.storemanagement.controller.Financeiro;

public class TelaSaidaUser extends JFrame {
    private JTextField campoValor;
    private JCheckBox checkPago;
    private JTextField campoDescricao;
    private JButton buttonRegistrar;
    private JButton buttonVoltar;

    public TelaSaidaUser() {
        setTitle("Registrar Saída Manual");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Campo Valor 
        campoValor = criarCampoComPlaceholder("Valor (ex: 123.45)", 30, 30, 320, 30);
        add(campoValor);

        checkPago = new JCheckBox("Está pago?");
        checkPago.setBounds(30, 70, 120, 30);
        add(checkPago);

        // Campo Descrição 
        campoDescricao = criarCampoComPlaceholder("Descrição", 30, 110, 320, 30);
        add(campoDescricao);

        // Botão Registrar Saída
        buttonRegistrar = new JButton("Registrar Saída");
        buttonRegistrar.setBounds(200, 150, 150, 30);
        add(buttonRegistrar);

        buttonRegistrar.addActionListener(e -> {
            try {
                float valor = Float.parseFloat(campoValor.getText());
                boolean isPago = checkPago.isSelected();
                String descricao = campoDescricao.getText();

                Financeiro financeiro = new Financeiro();
                financeiro.registrarSaidaUser(valor, isPago, descricao);

                JOptionPane.showMessageDialog(this, "Saída registrada com sucesso!");

                // Limpar campos e resetar placeholders
                resetarCampo(campoValor, "Valor (ex: 123.45)");
                checkPago.setSelected(false);
                resetarCampo(campoDescricao, "Descrição");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido! Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao registrar saída: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 150, 100, 30);
        add(buttonVoltar);
         buttonVoltar.addActionListener(e -> {
            System.out.println("Abrir tela de adicionar Usuario");
            new TelaFinanceiro().setVisible(true);  
            dispose();
        });
    }
        /// quando user digita o texto aqui vai sumiu viu kaio
       private JTextField criarCampoComPlaceholder(String placeholder, int x, int y, int width, int height) {
        JTextField campo = new JTextField(placeholder);
        campo.setBounds(x, y, width, height);
        campo.setForeground(java.awt.Color.GRAY);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(java.awt.Color.GRAY);
                    campo.setText(placeholder);
                }
            }
        });

        return campo;
    }

    private void resetarCampo(JTextField campo, String placeholder) {
        campo.setText(placeholder);
        campo.setForeground(java.awt.Color.GRAY);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaSaidaUser().setVisible(true));
    }
}