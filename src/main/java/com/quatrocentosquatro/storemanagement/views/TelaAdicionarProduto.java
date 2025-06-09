package com.quatrocentosquatro.storemanagement.views;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;
import com.quatrocentosquatro.storemanagement.model.Produto;

public class TelaAdicionarProduto extends JFrame {
    private JTextField campoNome;
    private JTextField campoMarca;
    private JTextField campoPreco;
    private JTextField campoQuantidade;
    private JTextField campoLote;
    private JTextField campoCodigoBarras;
    private JFormattedTextField campoDataValidade;
    private JTextField campoVolumeLitros;
    private JTextField campoPesoGramas;
    private JButton buttonAdicionar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelaAdicionarProduto() {
        setTitle("Adicionar Produto");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        campoNome = criarCampo("Nome", 30, 30);
        campoMarca = criarCampo("Marca", 250, 30);
        campoPreco = criarCampo("Preço", 470, 30);
        campoQuantidade = criarCampo("Quantidade", 30, 80);
        campoLote = criarCampo("Lote", 250, 80);
        campoCodigoBarras = criarCampo("Código de Barras", 470, 80);

        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
            campoDataValidade = new JFormattedTextField(mask);
            campoDataValidade.setBounds(30, 130, 180, 30);
            campoDataValidade.setText("dd/MM/yyyy");
            campoDataValidade.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (campoDataValidade.getText().equals("dd/MM/yyyy")) {
                        campoDataValidade.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (campoDataValidade.getText().isEmpty()) {
                        campoDataValidade.setText("dd/MM/yyyy");
                    }
                }
            });
            add(campoDataValidade);
        } catch (Exception e) {
            e.printStackTrace();
        }

        campoVolumeLitros = criarCampo("Volume (L)", 250, 130);
        campoPesoGramas = criarCampo("Peso (g)", 470, 130);

        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 350, 100, 30);
        add(buttonVoltar);
        buttonVoltar.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            dispose();
        });

        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 350, 100, 30);
        add(buttonHome);
        buttonHome.addActionListener(e -> {
            new TelaHome().setVisible(true);
            dispose();
        });

        buttonAdicionar = new JButton("Adicionar");
        buttonAdicionar.setBounds(550, 350, 100, 30);
        add(buttonAdicionar);
        buttonAdicionar.addActionListener(e -> {
            try {
                Produto produto = new Produto();
                produto.setNome(campoNome.getText());
                produto.setMarca(campoMarca.getText());
                produto.setPreco(Double.parseDouble(campoPreco.getText()));
                produto.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                produto.setLote(campoLote.getText());
                produto.setCodigoBarras(campoCodigoBarras.getText());

                String dataStr = campoDataValidade.getText();
                LocalDate dataValidade;
                try {
                    dataValidade = LocalDate.parse(dataStr, FORMATADOR_DATA);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/yyyy");
                    return;
                }
                produto.setDataValidade(dataValidade);

                produto.setVolumeLitros(Integer.parseInt(campoVolumeLitros.getText()));
                produto.setPesoGramas(Integer.parseInt(campoPesoGramas.getText()));

                // Usa a instância Singleton do GerenciarEstoque
                GerenciarEstoque.getInstancia().adicionarProduto(produto);

                JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
                new TelaEstoque().setVisible(true);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos numéricos corretamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar produto: " + ex.getMessage());
            }
        });
    }

    private JTextField criarCampo(String placeholder, int x, int y) {
        JTextField field = new JTextField(placeholder);
        field.setBounds(x, y, 180, 30);
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                }
            }
        });
        add(field);
        return field;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAdicionarProduto().setVisible(true));
    }
}
