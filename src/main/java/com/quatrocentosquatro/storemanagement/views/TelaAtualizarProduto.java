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

import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;
import com.quatrocentosquatro.storemanagement.model.Produto;

/**
 * @author Everton
 */
public class TelaAtualizarProduto extends JFrame {

    private JTextField campoProduto;
    private JTextField campoMarca;
    private JTextField campoPorId;
    private JTextField campoQuantidade;
    private JTextField campoLote;
    private JTextField campoCodigoBarras;
    private JTextField campoDataValidade;
    private JTextField campoPesoGramas;
    private JTextField campoPreco;
    private JButton buttonAtualizar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    private GerenciarEstoque gerenciador = new GerenciarEstoque();

    public TelaAtualizarProduto() {
        setTitle("Atualizar Produto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campo Produto
        campoProduto = new JTextField("Produto");
        campoProduto.setBounds(30, 30, 160, 30);
        campoProduto.addMouseListener(getPlaceholderClearListener(campoProduto, "Produto"));
        add(campoProduto);

        // Campo Marca
        campoMarca = new JTextField("Marca");
        campoMarca.setBounds(210, 30, 160, 30);
        campoMarca.addMouseListener(getPlaceholderClearListener(campoMarca, "Marca"));
        add(campoMarca);

        // Campo ID
        campoPorId = new JTextField("ID");
        campoPorId.setBounds(390, 30, 160, 30);
        campoPorId.addMouseListener(getPlaceholderClearListener(campoPorId, "ID"));
        add(campoPorId);

        // Campo Quantidade
        campoQuantidade = new JTextField("Quantidade");
        campoQuantidade.setBounds(30, 80, 160, 30);
        campoQuantidade.addMouseListener(getPlaceholderClearListener(campoQuantidade, "Quantidade"));
        add(campoQuantidade);

        // Campo Lote
        campoLote = new JTextField("Lote");
        campoLote.setBounds(210, 80, 160, 30);
        campoLote.addMouseListener(getPlaceholderClearListener(campoLote, "Lote"));
        add(campoLote);

        // Campo Código de Barras
        campoCodigoBarras = new JTextField("Código de Barras");
        campoCodigoBarras.setBounds(390, 80, 160, 30);
        campoCodigoBarras.addMouseListener(getPlaceholderClearListener(campoCodigoBarras, "Código de Barras"));
        add(campoCodigoBarras);

        // Campo Data de Validade
        campoDataValidade = new JTextField("Data Validade");
        campoDataValidade.setBounds(30, 130, 160, 30);
        campoDataValidade.addMouseListener(getPlaceholderClearListener(campoDataValidade, "Data Validade"));
        add(campoDataValidade);

        // Campo Peso
        campoPesoGramas = new JTextField("Peso (g)");
        campoPesoGramas.setBounds(210, 130, 160, 30);
        campoPesoGramas.addMouseListener(getPlaceholderClearListener(campoPesoGramas, "Peso (g)"));
        add(campoPesoGramas);

        // Campo Preço
        campoPreco = new JTextField("Preço");
        campoPreco.setBounds(390, 130, 160, 30);
        campoPreco.addMouseListener(getPlaceholderClearListener(campoPreco, "Preço"));
        add(campoPreco);

        // Botão Cadastrar (Atualizar)
        buttonAtualizar = new JButton("Atualizar");
        buttonAtualizar.setBounds(390, 300, 120, 30);
        add(buttonAtualizar );

        buttonAtualizar .addActionListener(e -> {
            try {
                Produto produto = new Produto();
                produto.setNome(campoProduto.getText());
                produto.setMarca(campoMarca.getText());
                produto.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                produto.setLote(campoLote.getText());
                produto.setCodigoBarras(campoCodigoBarras.getText());
                produto.setDataValidade(LocalDate.parse(campoDataValidade.getText())); // String
                produto.setPesoGramas(Integer.parseInt(campoPesoGramas.getText()));
                produto.setPreco(Float.parseFloat(campoPreco.getText()));

                int id = Integer.parseInt(campoPorId.getText().replace("ID: ", "").trim());

                gerenciador.atualizarProduto(id, produto);

                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos numéricos.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);

        buttonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaEstoque().setVisible(true);
                dispose();
            }
        });

        // Botão Home
        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);

        buttonHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaHome().setVisible(true);
                dispose();
            }
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
        SwingUtilities.invokeLater(() -> new TelaAtualizarProduto().setVisible(true));
    }
}
